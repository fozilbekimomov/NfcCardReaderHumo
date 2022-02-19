package uz.android.creditCardNfcReader.iso7816emv

class ByteArrayWrapper private constructor(private val data: ByteArray) {
    private val hashcode: Int = data.contentHashCode()
    override fun equals(other: Any?): Boolean {
        return if (other !is ByteArrayWrapper) {
            false
        } else data.contentEquals(other.data)
    }

    override fun hashCode(): Int {
        return hashcode
    }

    companion object {
        @JvmStatic
        fun wrapperAround(data: ByteArray?): ByteArrayWrapper {
            if (data == null) {
                throw NullPointerException()
            }
            return ByteArrayWrapper(data)
        }
    }

}