package uz.android.creditCardNfcReader.model

import uz.android.creditCardNfcReader.enums.EmvCardScheme

/**
 * Bean used to describe data in EMV card
 *
 */
class EmvCard : AbstractData() {
    /**
     * Method used to get the field aid
     *
     * @return the aid
     */
    /**
     * Setter for the field aid
     *
     * @param aid
     * the aid to set
     */
    /**
     * Card AID
     */
    var aid: String? = null
    /**
     * Method used to get the field holderLastname
     *
     * @return the holderLastname
     */
    /**
     * Setter for the field holderLastname
     *
     * @param holderLastname
     * the holderLastname to set
     */
    /**
     * Holder Lastname
     */
    var holderLastname: String? = null
    /**
     * Method used to get the field holderFirstname
     *
     * @return the holderFirstname
     */
    /**
     * Setter for the field holderFirstname
     *
     * @param holderFirstname
     * the holderFirstname to set
     */
    /**
     * Holder Firstname
     */
    var holderFirstname: String? = null
    /**
     * Method used to get the field cardNumber
     *
     * @return the cardNumber
     */
    /**
     * Setter for the field cardNumber
     *
     * @param cardNumber
     * the cardNumber to set
     */
    /**
     * Card number
     */
    var cardNumber: String? = null
    /**
     * Method used to get the field expireDate
     *
     * @return the expireDate
     */
    /**
     * Setter for the field expireDate
     *
     * @param expireDate
     * the expireDate to set
     */
    /**
     * Expiration date
     */
    var expireDate: String? = null
    /**
     * Method used to get the field type
     *
     * @return the type
     */
    /**
     * Setter for the field type
     *
     * @param type
     * the type to set
     */
    /**
     * Card type
     */
    var type: EmvCardScheme? = null
    /**
     * Method used to get the field leftPinTry
     *
     * @return the leftPinTry
     */
    /**
     * Setter for the field leftPinTry
     *
     * @param leftPinTry
     * the leftPinTry to set
     */
    /**
     * Left PIN try
     */
    var leftPinTry = 0
    /**
     * Method used to get the field applicationLabel
     *
     * @return the applicationLabel
     */
    /**
     * Setter for the field applicationLabel
     *
     * @param applicationLabel
     * the applicationLabel to set
     */
    /**
     * Application label
     */
    var applicationLabel: String? = null
    /**
     * Method used to get the field listTransactions
     *
     * @return the listTransactions
     */
    /**
     * Setter for the field listTransactions
     *
     * @param listTransactions
     * the listTransactions to set
     */
    /**
     * List of issued payment
     */
    var listTransactions: List<EmvTransactionRecord>? = null
    /**
     * Method used to get the field atrDescription
     *
     * @return the atrDescription
     */
    /**
     * Setter for the field atrDescription
     *
     * @param atrDescription
     * the atrDescription to set
     */
    /**
     * List of Atr description
     */
    var atrDescription: Collection<String>? = null
    /**
     * Method used to get the field service
     *
     * @return the service
     */
    /**
     * Setter for the field service
     *
     * @param service
     * the service to set
     */
    /**
     * Card services
     */
    var service: Service? = null
    /**
     * Method used to get the field nfcLocked
     *
     * @return the nfcLocked
     */
    /**
     * Setter for the field nfcLocked
     *
     * @param nfcLocked
     * the nfcLocked to set
     */
    /**
     * Indicate if the nfc is locked on the card
     */
    var isNfcLocked = false
    override fun equals(arg0: Any?): Boolean {
        return arg0 is EmvCard && cardNumber != null && cardNumber == arg0.cardNumber
    }

    companion object {
        /**
         * Generated serial UID
         */
        private const val serialVersionUID = 736740432469989941L
    }
}