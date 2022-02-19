package uz.android.creditCardNfcReader.utils

import fr.devnied.bitlib.BytesUtils
import org.apache.commons.lang3.ArrayUtils
import org.apache.commons.lang3.StringUtils
import uz.android.creditCardNfcReader.enums.SwEnum.Companion.getSW
import uz.android.creditCardNfcReader.enums.TagValueTypeEnum
import uz.android.creditCardNfcReader.exception.TlvException
import uz.android.creditCardNfcReader.iso7816emv.EmvTags.getNotNull
import uz.android.creditCardNfcReader.iso7816emv.ITag
import uz.android.creditCardNfcReader.iso7816emv.TLV
import uz.android.creditCardNfcReader.iso7816emv.TagAndLength
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import kotlin.experimental.and

/**
 * List of utils methods to manipulate TLV
 */
object TlvUtil {
    /**
     * Method used to find Tag with ID
     *
     * @param tagIdBytes
     * the tag to find
     * @return the tag found
     */
    private fun searchTagById(tagIdBytes: ByteArray): ITag? {
        return getNotNull(tagIdBytes) // TODO take app (IIN or RID) into consideration
    }

    /**
     * Method used to search tag with id
     *
     * @param stream
     * Byte array in a stream
     * @return Tag found
     */
    private fun searchTagById(stream: ByteArrayInputStream): ITag? {
        return searchTagById(readTagIdBytes(stream))
    }

    // This is just a list of Tag And Lengths (eg DOLs)
    fun getFormattedTagAndLength(data: ByteArray?, indentLength: Int): String {
        val buf = StringBuilder()
        val indent = getSpaces(indentLength)
        val stream = ByteArrayInputStream(data)
        var firstLine = true
        while (stream.available() > 0) {
            if (firstLine) {
                firstLine = false
            } else {
                buf.append("\n")
            }
            buf.append(indent)
            val tag = searchTagById(stream)
            val length = readTagLength(stream)
            buf.append(prettyPrintHex(tag!!.tagBytes))
            buf.append(" ")
            buf.append(String.format("%02x", length))
            buf.append(" -- ")
            buf.append(tag.name)
        }
        return buf.toString()
    }

    fun readTagIdBytes(stream: ByteArrayInputStream): ByteArray {
        val tagBAOS = ByteArrayOutputStream()
        val tagFirstOctet = stream.read().toByte()
        tagBAOS.write(tagFirstOctet.toInt())

        // Find TAG bytes
        val MASK = 0x1F.toByte()
        if (tagFirstOctet and MASK == MASK) { // EMV book 3, Page 178 or Annex B1 (EMV4.3)
            // Tag field is longer than 1 byte
            do {
                val nextOctet = stream.read()
                if (nextOctet < 0) {
                    break
                }
                val tlvIdNextOctet = nextOctet.toByte()
                tagBAOS.write(tlvIdNextOctet.toInt())
                if (!BytesUtils.matchBitByBitIndex(
                        tlvIdNextOctet.toInt(),
                        7
                    ) || BytesUtils.matchBitByBitIndex(tlvIdNextOctet.toInt(), 7)
                    && ((tlvIdNextOctet and 0x7f).toInt() == 0)
                ) {
                    break
                }
            } while (true)
        }
        return tagBAOS.toByteArray()
    }

    fun readTagLength(stream: ByteArrayInputStream): Int {
        // Find LENGTH bytes
        val length: Int
        var tmpLength = stream.read()
        if (tmpLength < 0) {
            throw TlvException("Negative length: $tmpLength")
        }
        if (tmpLength <= 127) { // 0111 1111
            // short length form
            length = tmpLength
        } else if (tmpLength == 128) { // 1000 0000
            // length identifies indefinite form, will be set later
            // indefinite form is not specified in ISO7816-4, but we include it here for completeness
            length = tmpLength
        } else {
            // long length form
            val numberOfLengthOctets = tmpLength and 127 // turn off 8th bit
            tmpLength = 0
            for (i in 0 until numberOfLengthOctets) {
                val nextLengthOctet = stream.read()
                if (nextLengthOctet < 0) {
                    throw TlvException("EOS when reading length bytes")
                }
                tmpLength = tmpLength shl 8
                tmpLength = tmpLength or nextLengthOctet
            }
            length = tmpLength
        }
        return length
    }

    fun getNextTLV(stream: ByteArrayInputStream): TLV {
        if (stream.available() < 2) {
            throw TlvException("Error parsing data. Available bytes < 2 . Length=" + stream.available())
        }

        // ISO/IEC 7816 uses neither '00' nor 'FF' as tag value.
        // Before, between, or after TLV-coded data objects,
        // '00' or 'FF' bytes without any meaning may occur
        // (for example, due to erased or modified TLV-coded data objects).
        stream.mark(0)
        var peekInt = stream.read()
        var peekByte = peekInt.toByte()
        // peekInt == 0xffffffff indicates EOS
        while (peekInt != -1 && (peekByte == 0xFF.toByte() || peekByte == 0x00.toByte())) {
            stream.mark(0) // Current position
            peekInt = stream.read()
            peekByte = peekInt.toByte()
        }
        stream.reset() // Reset back to the last known position without 0x00 or 0xFF
        if (stream.available() < 2) {
            throw TlvException("Error parsing data. Available bytes < 2 . Length=" + stream.available())
        }
        val tagIdBytes = readTagIdBytes(stream)

        // We need to get the raw length bytes.
        // Use quick and dirty workaround
        stream.mark(0)
        val posBefore = stream.available()
        // Now parse the lengthbyte(s)
        // This method will read all length bytes. We can then find out how many bytes was read.
        var length = readTagLength(stream) // Decoded
        // Now find the raw (encoded) length bytes
        val posAfter = stream.available()
        stream.reset()
        val lengthBytes = ByteArray(posBefore - posAfter)
        if (lengthBytes.size < 1 || lengthBytes.size > 4) {
            throw TlvException("Number of length bytes must be from 1 to 4. Found " + lengthBytes.size)
        }
        stream.read(lengthBytes, 0, lengthBytes.size)
        val rawLength = BytesUtils.byteArrayToInt(lengthBytes)
        val valueBytes: ByteArray
        val tag = searchTagById(tagIdBytes)

        // Find VALUE bytes
        if (rawLength == 128) { // 1000 0000
            // indefinite form
            stream.mark(0)
            var prevOctet = 1
            var curOctet: Int
            var len = 0
            while (true) {
                len++
                curOctet = stream.read()
                if (curOctet < 0) {
                    throw TlvException(
                        "Error parsing data. TLV " + "length byte indicated indefinite length, but EOS "
                                + "was reached before 0x0000 was found" + stream.available()
                    )
                }
                if (prevOctet == 0 && curOctet == 0) {
                    break
                }
                prevOctet = curOctet
            }
            len -= 2
            valueBytes = ByteArray(len)
            stream.reset()
            stream.read(valueBytes, 0, len)
            length = len
        } else {
            if (stream.available() < length) {
                throw TlvException(
                    "Length byte(s) indicated " + length + " value bytes, but only " + stream.available()
                            + " " + (if (stream.available() > 1) "are" else "is") + " available"
                )
            }
            // definite form
            valueBytes = ByteArray(length)
            stream.read(valueBytes, 0, length)
        }

        // Remove any trailing 0x00 and 0xFF
        stream.mark(0)
        peekInt = stream.read()
        peekByte = peekInt.toByte()
        while (peekInt != -1 && (peekByte == 0xFF.toByte() || peekByte == 0x00.toByte())) {
            stream.mark(0)
            peekInt = stream.read()
            peekByte = peekInt.toByte()
        }
        stream.reset() // Reset back to the last known position without 0x00 or 0xFF
        return TLV(tag!!, length, lengthBytes, valueBytes)
    }

    /**
     * Method used get Tag value as String
     *
     * @param tag
     * tag type
     * @param value
     * tag value
     * @return
     */
    private fun getTagValueAsString(tag: ITag, value: ByteArray): String {
        val buf = StringBuilder()
        when (tag.tagValueType) {
            TagValueTypeEnum.TEXT -> {
                buf.append("=")
                buf.append(String(value))
            }
            TagValueTypeEnum.NUMERIC -> buf.append("NUMERIC")
            TagValueTypeEnum.BINARY -> buf.append("BINARY")
            TagValueTypeEnum.MIXED -> {
                buf.append("=")
                buf.append(getSafePrintChars(value))
            }
            TagValueTypeEnum.DOL -> buf.append("")
            else -> {}
        }
        return buf.toString()
    }

    /**
     * Method used to parser Tag and length
     *
     * @param data
     * data to parse
     * @return list of tag and length
     */
    @JvmStatic
    fun parseTagAndLength(data: ByteArray?): List<TagAndLength> {
        val tagAndLengthList: MutableList<TagAndLength> = ArrayList()
        if (data != null) {
            val stream = ByteArrayInputStream(data)
            while (stream.available() > 0) {
                if (stream.available() < 2) {
                    throw TlvException("Data length < 2 : " + stream.available())
                }
                val tag = searchTagById(readTagIdBytes(stream))
                val tagValueLength = readTagLength(stream)
                tagAndLengthList.add(TagAndLength(tag!!, tagValueLength))
            }
        }
        return tagAndLengthList
    }

    fun prettyPrintAPDUResponse(data: ByteArray?, startPos: Int, length: Int): String {
        val tmp = ByteArray(length - startPos)
        System.arraycopy(data, startPos, tmp, 0, length)
        return prettyPrintAPDUResponse(tmp, 0)
    }

    /**
     * Method used to get the list of TLV inside the parameter tag specified in parameter
     *
     * @param pData
     * data to parse
     * @param pTag
     * tag to find
     * @param pAdd
     * @return the list of TLV tag inside
     */
    fun getlistTLV(pData: ByteArray?, pTag: ITag, pAdd: Boolean): List<TLV> {
        val list: MutableList<TLV> = ArrayList()
        val stream = ByteArrayInputStream(pData)
        while (stream.available() > 0) {
            val tlv = getNextTLV(stream)
            if (pAdd) {
                list.add(tlv)
            } else if (tlv.tag.isConstructed) {
                list.addAll(getlistTLV(tlv.valueBytes, pTag, tlv.tag === pTag))
            }
        }
        return list
    }

    /**
     * Method used to get the list of TLV corresponding to tags specified in parameters
     *
     * @param pData
     * data to parse
     * @param pTag
     * tags to find
     * @param pAdd
     * @return the list of TLV
     */
    @JvmStatic
    fun getlistTLV(pData: ByteArray?, vararg pTag: ITag?): List<TLV> {
        val list: MutableList<TLV> = ArrayList()
        val stream = ByteArrayInputStream(pData)
        while (stream.available() > 0) {
            val tlv = getNextTLV(stream)
            if (ArrayUtils.contains(pTag, tlv.tag)) {
                list.add(tlv)
            } else if (tlv.tag.isConstructed) {
                list.addAll(getlistTLV(tlv.valueBytes, *pTag))
            }
        }
        return list
    }

    /**
     * Method used to get Tag value
     *
     * @param pData
     * data
     * @param pTag
     * tag to find
     * @return tag value or null
     */
    @JvmStatic
    fun getValue(pData: ByteArray?, vararg pTag: ITag?): ByteArray? {
        var ret: ByteArray? = null
        if (pData != null) {
            val stream = ByteArrayInputStream(pData)
            while (stream.available() > 0) {
                val tlv = getNextTLV(stream)
                if (ArrayUtils.contains(pTag, tlv.tag)) {
                    return tlv.valueBytes
                } else if (tlv.tag.isConstructed) {
                    ret = getValue(tlv.valueBytes, *pTag)
                    if (ret != null) {
                        break
                    }
                }
            }
        }
        return ret
    }

    @JvmOverloads
    fun prettyPrintAPDUResponse(data: ByteArray?, indentLength: Int = 0): String {
        val buf = StringBuilder()
        val stream = ByteArrayInputStream(data)
        while (stream.available() > 0) {
            buf.append("\n")
            if (stream.available() == 2) {
                stream.mark(0)
                val value = ByteArray(2)
                try {
                    stream.read(value)
                } catch (e: IOException) {
                }
                val sw = getSW(value)
                if (sw != null) {
                    buf.append(getSpaces(0))
                    buf.append(BytesUtils.bytesToString(value)).append(" -- ")
                    buf.append(sw.detail)
                    continue
                }
                stream.reset()
            }
            buf.append(getSpaces(indentLength))
            val tlv = getNextTLV(stream)
            val tagBytes = tlv.tagBytes
            val lengthBytes = tlv.rawEncodedLengthBytes
            val valueBytes = tlv.valueBytes
            val tag = tlv.tag
            buf.append(prettyPrintHex(tagBytes))
            buf.append(" ")
            buf.append(prettyPrintHex(lengthBytes))
            buf.append(" -- ")
            buf.append(tag.name)
            val extraIndent = (lengthBytes.size + tagBytes!!.size) * 3
            if (tag.isConstructed) {
                // indentLength += extraIndent; //TODO check this
                // Recursion
                buf.append(prettyPrintAPDUResponse(valueBytes, indentLength + extraIndent))
            } else {
                buf.append("\n")
                if (tag.tagValueType === TagValueTypeEnum.DOL) {
                    buf.append(getFormattedTagAndLength(valueBytes, indentLength + extraIndent))
                } else {
                    buf.append(getSpaces(indentLength + extraIndent))
                    buf.append(
                        prettyPrintHex(
                            BytesUtils.bytesToStringNoSpace(valueBytes),
                            indentLength + extraIndent
                        )
                    )
                    buf.append(" (")
                    buf.append(getTagValueAsString(tag, valueBytes))
                    buf.append(")")
                }
            }
        }
        return buf.toString()
    }

    fun getSpaces(length: Int): String {
        return StringUtils.leftPad(StringUtils.EMPTY, length)
    }

    fun prettyPrintHex(data: ByteArray?, indent: Int): String {
        return prettyPrintHex(BytesUtils.bytesToStringNoSpace(data), indent, true)
    }

    fun prettyPrintHex(data: ByteArray?): String {
        return prettyPrintHex(BytesUtils.bytesToStringNoSpace(data), 0, true)
    }

    @JvmOverloads
    fun prettyPrintHex(`in`: String, indent: Int = 0, wrapLines: Boolean = true): String {
        val buf = StringBuilder()
        for (i in 0 until `in`.length) {
            val c = `in`[i]
            buf.append(c)
            val nextPos = i + 1
            if (wrapLines && nextPos % 32 == 0 && nextPos != `in`.length) {
                buf.append("\n").append(getSpaces(indent))
            } else if (nextPos % 2 == 0 && nextPos != `in`.length) {
                buf.append(" ")
            }
        }
        return buf.toString()
    }

    fun getSafePrintChars(byteArray: ByteArray?): String {
        return if (byteArray == null) {
            ""
        } else getSafePrintChars(byteArray, 0, byteArray.size)
    }

    fun getSafePrintChars(byteArray: ByteArray?, startPos: Int, length: Int): String {
        if (byteArray == null) {
            return ""
        }
        require(byteArray.size >= startPos + length) {
            ("startPos(" + startPos + ")+length(" + length + ") > byteArray.length("
                    + byteArray.size + ")")
        }
        val buf = StringBuilder()
        for (i in startPos until startPos + length) {
            if (byteArray[i] >= 0x20.toByte() && byteArray[i] < 0x7F.toByte()) {
                buf.append(byteArray[i].toInt().toChar())
            } else {
                buf.append(".")
            }
        }
        return buf.toString()
    }

    /**
     * Method used to get length of all Tags
     *
     * @param pList
     * tag length list
     * @return the sum of tag length
     */
    @JvmStatic
    fun getLength(pList: List<TagAndLength>?): Int {
        var ret = 0
        if (pList != null) {
            for (tl in pList) {
                ret += tl.length
            }
        }
        return ret
    }
}