package uz.android.creditCardNfcReader.parser

import uz.android.creditCardNfcReader.exception.CommunicationException

/**
 * Interface for provider for transmit command to card
 */
interface IProvider {
    /**
     * Method used to transmit and receive card response
     *
     * @param pCommand command to send to card
     * @return byte array returned by card
     */
    @Throws(CommunicationException::class)
    fun transceive(pCommand: ByteArray?): ByteArray?
}