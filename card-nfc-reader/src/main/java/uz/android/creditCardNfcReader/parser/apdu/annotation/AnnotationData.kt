package uz.android.creditCardNfcReader.parser.apdu.annotation

import fr.devnied.bitlib.BytesUtils
import uz.android.creditCardNfcReader.iso7816emv.EmvTags.find
import uz.android.creditCardNfcReader.iso7816emv.ITag
import java.lang.reflect.Field

/**
 * Bean which manage all annotation data
 */
class AnnotationData : Comparable<AnnotationData>, Cloneable {
    /**
     * The size of the field
     */
    private var size = 0
    /**
     * Method used to get the field index
     *
     * @return the index
     */
    /**
     * Index of the field
     */
    var index = 0
        private set
    /**
     * Method used to get the field readHexa
     *
     * @return the readHexa
     */
    /**
     * read String as hexa value
     */
    var isReadHexa = false
        private set

    /**
     * Field to modify
     */
    private var field: Field? = null
    /**
     * Method used to get the field dateStandard
     *
     * @return the dateStandard
     */
    /**
     * The date standard
     */
    var dateStandard = 0
        private set
    /**
     * Method used to get the field format
     *
     * @return the format
     */
    /**
     * Date format
     */
    var format: String? = null
        private set
    /**
     * Method used to get the field tag
     *
     * @return the tag
     */
    /**
     * Tag
     */
    var tag: ITag? = null
        private set
    /**
     * Method used to get the field skip
     *
     * @return the skip
     */
    /**
     * Setter for the field skip
     *
     * @param skip the skip to set
     */
    /**
     * Skip data
     */
    var isSkip = false

    /**
     * Comparable method {@inheritDoc}
     */
    override fun compareTo(pO: AnnotationData): Int {
        return Integer.valueOf(index).compareTo(pO.index)
    }

    /**
     * Equals method {@inheritDoc}
     */
    override fun equals(pObj: Any?): Boolean {
        var ret = false
        if (pObj is AnnotationData) {
            ret = index == pObj.index
        }
        return ret
    }

    /**
     * Method used to get the field size
     *
     * @return the size
     */
    fun getSize(): Int {
        return size
    }

    /**
     * Setter for the field size
     *
     * @param size the size to set
     */
    fun setSize(size: Int) {
        this.size = size
    }

    /**
     * Method used to get the field field
     *
     * @return the field
     */
    fun getField(): Field? {
        return field
    }

    /**
     * Setter for the field field
     *
     * @param field the field to set
     */
    fun setField(field: Field?) {
        this.field = field
    }

    /**
     * Initialization from annotation
     *
     * @param pData annotation data
     */
    fun initFromAnnotation(pData: Data) {
        dateStandard = pData.dateStandard
        format = pData.format
        index = pData.index
        isReadHexa = pData.readHexa
        size = pData.size
        if (pData.tag != null) {
            tag = find(BytesUtils.fromString(pData.tag))
        }
    }

    @Throws(CloneNotSupportedException::class)
    public override fun clone(): Any {
        val data = AnnotationData()
        data.dateStandard = dateStandard
        data.field = field
        data.format = format
        data.index = index
        data.isReadHexa = isReadHexa
        data.size = size
        data.tag = tag
        return data
    }

    override fun hashCode(): Int {
        var result = size
        result = 31 * result + index
        result = 31 * result + isReadHexa.hashCode()
        result = 31 * result + (field?.hashCode() ?: 0)
        result = 31 * result + dateStandard
        result = 31 * result + (format?.hashCode() ?: 0)
        result = 31 * result + (tag?.hashCode() ?: 0)
        result = 31 * result + isSkip.hashCode()
        return result
    }
}