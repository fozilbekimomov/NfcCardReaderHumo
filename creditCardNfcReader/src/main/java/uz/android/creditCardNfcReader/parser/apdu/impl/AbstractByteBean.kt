package uz.android.creditCardNfcReader.parser.apdu.impl

import fr.devnied.bitlib.BitUtils
import org.slf4j.LoggerFactory
import uz.android.creditCardNfcReader.iso7816emv.ITag
import uz.android.creditCardNfcReader.iso7816emv.TagAndLength
import uz.android.creditCardNfcReader.model.AbstractData
import uz.android.creditCardNfcReader.parser.apdu.annotation.AnnotationData
import uz.android.creditCardNfcReader.parser.apdu.annotation.AnnotationUtils
import java.lang.reflect.Field

/**
 * Abstract class for all object to parse
 */
abstract class AbstractByteBean<T> : AbstractData(),
    uz.android.creditCardNfcReader.parser.apdu.IFile {
    /**
     * Method to get the annotation set from the current class
     *
     * @return An annotation set which contain all annotation data
     */
    private fun getAnnotationSet(pTags: List<TagAndLength>?): Collection<AnnotationData>? {
        val ret: Collection<AnnotationData>?
        if (pTags != null) {
            val data: Map<ITag?, AnnotationData> =
                AnnotationUtils.instance.getMap()[javaClass.name]!!
            ret = ArrayList(data.size)
            for (tal in pTags) {
                var ann = data[tal.tag]
                if (ann != null) {
                    ann.setSize(tal.length * BitUtils.BYTE_SIZE)
                } else {
                    ann = AnnotationData()
                    ann.isSkip = true
                    ann.setSize(tal.length * BitUtils.BYTE_SIZE)
                }
                ret.add(ann)
            }
        } else {
            ret = AnnotationUtils.instance.getMapSet()[javaClass.name]
        }
        return ret
    }

    /**
     * Method to parse byte data
     *
     * @param pData
     * byte to parse
     * @param pTags
     */
    override fun parse(pData: ByteArray, pTags: List<TagAndLength>) {
        val set = getAnnotationSet(pTags)
        val bit = BitUtils(pData)
        val it = set!!.iterator()
        while (it.hasNext()) {
            val data = it.next()
            if (data.isSkip) {
                bit.addCurrentBitIndex(data.getSize())
            } else {
                val obj = DataFactory.getObject(data, bit)
                setField(data.getField(), this, obj)
            }
        }
    }

    /**
     * Method used to set the value of a field
     *
     * @param field
     * the field to set
     * @param pData
     * Object containing the field
     * @param pValue
     * the value of the field
     */
    protected fun setField(
        field: Field?,
        pData: uz.android.creditCardNfcReader.parser.apdu.IFile?,
        pValue: Any?
    ) {
        if (field != null) {
            try {
                field[pData] = pValue
            } catch (e: IllegalArgumentException) {
                LOGGER.error("Parameters of fied.set are not valid", e)
            } catch (e: IllegalAccessException) {
                LOGGER.error("Impossible to set the Field :" + field.name, e)
            }
        }
    }

    companion object {
        /**
         * Generated serial UID
         */
        private const val serialVersionUID = -2016039522844322383L

        /**
         * Logger of the class
         */
        private val LOGGER = LoggerFactory.getLogger(AbstractByteBean::class.java.name)
    }
}