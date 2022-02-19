package uz.android.creditCardNfcReader.exception

/**
 * Exception during TLV reading
 *
 */
class TlvException
/**
 * Constructor using field
 *
 * @param pCause
 * cause
 */
    (pCause: String?) : RuntimeException(pCause)