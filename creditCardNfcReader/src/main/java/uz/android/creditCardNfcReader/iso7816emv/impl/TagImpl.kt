package uz.android.creditCardNfcReader.iso7816emv.impl

import uz.android.creditCardNfcReader.enums.TagTypeEnum
import uz.android.creditCardNfcReader.enums.TagValueTypeEnum
import uz.android.creditCardNfcReader.iso7816emv.ITag
import java.util.*

class TagImpl(
    idBytes: ByteArray?,
    tagValueType: TagValueTypeEnum?,
    name: String?,
    description: String?
) : ITag {
    override val tagBytes: ByteArray
    override val name: String?
    override val description: String?
    override val tagValueType: TagValueTypeEnum
    override var tagClass: ITag.Class? = null
    override var type: TagTypeEnum? = null

    constructor(
        id: String?,
        tagValueType: TagValueTypeEnum?,
        name: String?,
        description: String?
    ) : this(
        uz.android.creditCardNfcReader.utils.BytesUtils.fromString(id),
        tagValueType,
        name,
        description
    )

    override val isConstructed: Boolean
        get() = type === TagTypeEnum.CONSTRUCTED

    override fun equals(other: Any?): Boolean {
        if (other !is ITag) {
            return false
        }
        val that = other
        return if (tagBytes.size != that.tagBytes?.size) {
            false
        } else Arrays.equals(tagBytes, that.tagBytes)
    }

    override fun hashCode(): Int {
        var hash = 3
        hash = 59 * hash + Arrays.hashCode(tagBytes)
        return hash
    }

    override val numTagBytes: Int
        get() = tagBytes.size

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Tag[")
        sb.append(uz.android.creditCardNfcReader.utils.BytesUtils.bytesToString(tagBytes))
        sb.append("] Name=")
        sb.append(name)
        sb.append(", TagType=")
        sb.append(type)
        sb.append(", ValueType=")
        sb.append(tagValueType)
        sb.append(", Class=")
        sb.append(tagClass)
        return sb.toString()
    }

    init {
        requireNotNull(idBytes) { "Param id cannot be null" }
        require(idBytes.isNotEmpty()) { "Param id cannot be empty" }
        requireNotNull(tagValueType) { "Param tagValueType cannot be null" }
        tagBytes = idBytes
        this.name = name
        this.description = description
        this.tagValueType = tagValueType
        type = if (fr.devnied.bitlib.BytesUtils.matchBitByBitIndex(tagBytes[0].toInt(), 5)) {
            TagTypeEnum.CONSTRUCTED
        } else {
            TagTypeEnum.PRIMITIVE
        }
        // Bits 8 and 7 of the first byte of the tag field indicate a class.
        // The value 00 indicates a data object of the universal class.
        // The value 01 indicates a data object of the application class.
        // The value 10 indicates a data object of the context-specific class.
        // The value 11 indicates a data object of the private class.
        val classValue = (tagBytes[0].toLong() ushr 6 and 0x03).toByte()
        tagClass = when (classValue) {
            0x01.toByte() -> ITag.Class.APPLICATION
            0x02.toByte() -> ITag.Class.CONTEXT_SPECIFIC
            0x03.toByte() -> ITag.Class.PRIVATE
            else -> ITag.Class.UNIVERSAL
        }
    }
}