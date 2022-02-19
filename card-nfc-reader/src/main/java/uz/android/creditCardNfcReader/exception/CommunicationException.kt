package uz.android.creditCardNfcReader.exception

import java.io.IOException

/**
 * Exception during communication with EMV card
 *
 */
class CommunicationException
/**
 * Default constructor
 *
 * @param pMessage
 * Exception message
 */
    (pMessage: String?) : IOException(pMessage)