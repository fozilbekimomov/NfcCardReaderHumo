package uz.android.creditCardNfcReader.parser.apdu.impl

import fr.devnied.bitlib.BitUtils
import org.slf4j.LoggerFactory
import uz.android.creditCardNfcReader.model.enums.IKeyEnum
import uz.android.creditCardNfcReader.parser.apdu.annotation.AnnotationData
import uz.android.creditCardNfcReader.utils.EnumUtils
import java.util.*

/**
 * Factory to parse data
 */
object DataFactory {
    /**
     * Logger of this class
     */
    val LOGGER = LoggerFactory.getLogger(DataFactory::class.java.name)

    /**
     * Constant for EN1545-1 (Date format)
     */
    const val BCD_DATE = 1

    /**
     * Half byte size
     */
    const val HALF_BYTE_SIZE = 4

    /**
     * BCD format
     */
    const val BCD_FORMAT = "BCD_Format"

    /**
     * Method to get a date from the bytes array
     *
     * @param pAnnotation annotation data
     * @param pBit        table bytes
     * @return The date read of null
     */
    private fun getDate(pAnnotation: AnnotationData, pBit: BitUtils): Date? {
        var date: Date? = null
        date =
            if (pAnnotation.dateStandard == BCD_DATE) {
                pBit.getNextDate(pAnnotation.getSize(), pAnnotation.format, true)
            } else {
                pBit.getNextDate(pAnnotation.getSize(), pAnnotation.format)
            }
        return date
    }

    /**
     * This method is used to get an integer
     *
     * @param pAnnotation annotation
     * @param pBit        bit array
     */
    private fun getInteger(pAnnotation: AnnotationData, pBit: BitUtils): Int {
        return pBit.getNextInteger(pAnnotation.getSize())
    }

    /**
     * Method to read and object from the bytes tab
     *
     * @param pAnnotation all information data
     * @param pBit        bytes tab
     * @return an object
     */
    fun getObject(pAnnotation: AnnotationData, pBit: BitUtils): Any? {
        var obj: Any? = null
        val clazz = pAnnotation.getField()!!.type
        if (clazz == Int::class.java) {
            obj = getInteger(pAnnotation, pBit)
        } else if (clazz == Float::class.java) {
            obj = getFloat(pAnnotation, pBit)
        } else if (clazz == String::class.java) {
            obj = getString(pAnnotation, pBit)
        } else if (clazz == Date::class.java) {
            obj = getDate(pAnnotation, pBit)
        } else if (clazz == Boolean::class.java) {
            obj = pBit.nextBoolean
        } else if (clazz.isEnum) {
            obj = getEnum(pAnnotation, pBit)
        }
        return obj
    }

    /**
     * Method use to get float
     *
     * @param pAnnotation annotation
     * @param pBit        bit utils
     * @return
     */
    private fun getFloat(pAnnotation: AnnotationData, pBit: BitUtils): Float {
        var ret: Float? = null
        ret =
            if (BCD_FORMAT == pAnnotation.format) {
                pBit.getNextHexaString(pAnnotation.getSize()).toFloat()
            } else {
                getInteger(
                    pAnnotation,
                    pBit
                )
                    .toFloat()
            }
        return ret
    }

    /**
     * This method is used to get an enum with his key
     *
     * @param pAnnotation annotation
     * @param pBit        bit array
     */
    private fun getEnum(pAnnotation: AnnotationData, pBit: BitUtils): IKeyEnum {
        var value = 0
        try {
            value = pBit.getNextHexaString(pAnnotation.getSize())
                .toInt(if (pAnnotation.isReadHexa) 16 else 10)
        } catch (nfe: NumberFormatException) {
            // do nothing
        }
        return EnumUtils.getValue(value, pAnnotation.getField()!!.type as Class<out IKeyEnum?>)!!
    }

    /**
     * This method get a string (Hexa or ASCII) from a bit table
     *
     * @param pAnnotation annotation data
     * @param pBit        bit table
     * @return A string
     */
    private fun getString(pAnnotation: AnnotationData, pBit: BitUtils): String? {
        var obj: String? = null
        obj = if (pAnnotation.isReadHexa) {
            pBit.getNextHexaString(pAnnotation.getSize())
        } else {
            pBit.getNextString(pAnnotation.getSize()).trim { it <= ' ' }
        }
        return obj
    }
}