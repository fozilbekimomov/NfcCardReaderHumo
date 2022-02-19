package uz.android.creditCardNfcReader.iso7816emv


/**
 * Tag and length value
 *
 */
class TLV(tag: ITag, length: Int, rawEncodedLengthBytes: ByteArray, valueBytes: ByteArray?) {
    /**
     * Method used to get the field tag
     *
     * @return the tag
     */
    /**
     * Setter for the field tag
     *
     * @param tag
     * the tag to set
     */
    /**
     * Tag
     */
    var tag: ITag
    /**
     * Method used to get the field rawEncodedLengthBytes
     *
     * @return the rawEncodedLengthBytes
     */
    /**
     * Setter for the field rawEncodedLengthBytes
     *
     * @param rawEncodedLengthBytes
     * the rawEncodedLengthBytes to set
     */
    /**
     * length in bytes
     */
    var rawEncodedLengthBytes: ByteArray
    /**
     * Method used to get the field valueBytes
     *
     * @return the valueBytes
     */
    /**
     * Setter for the field valueBytes
     *
     * @param valueBytes
     * the valueBytes to set
     */
    /**
     * Value in bytes
     */
    var valueBytes: ByteArray
    /**
     * Method used to get the field length
     *
     * @return the length
     */
    /**
     * Setter for the field length
     *
     * @param length
     * the length to set
     */
    /**
     * Tag length
     */
    var length: Int

    /**
     * Get tag bytes
     *
     * @return tag bytes
     */
    val tagBytes: ByteArray?
        get() = tag.tagBytes

    /**
     *
     * @param tag
     * @param length
     * contains the number of value bytes (parsed from the rawEncodedLengthBytes)
     * @param rawLengthBytes
     * the raw encoded length bytes
     * @param valueBytes
     */
    init {
        require(!(valueBytes == null || length != valueBytes.size)) {
            // Assert
            "length != bytes.length"
        }
        this.tag = tag
        this.rawEncodedLengthBytes = rawEncodedLengthBytes
        this.valueBytes = valueBytes
        this.length = length
    }
}