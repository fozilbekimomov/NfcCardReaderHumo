package uz.android.creditCardNfcReader.model

import uz.android.creditCardNfcReader.model.enums.CountryCodeEnum
import uz.android.creditCardNfcReader.model.enums.CurrencyEnum
import uz.android.creditCardNfcReader.model.enums.TransactionTypeEnum
import uz.android.creditCardNfcReader.parser.apdu.annotation.Data
import uz.android.creditCardNfcReader.parser.apdu.impl.AbstractByteBean
import uz.android.creditCardNfcReader.parser.apdu.impl.DataFactory
import java.io.Serializable
import java.util.*

/**
 * Bean used to describe EMV transaction record
 */
class EmvTransactionRecord : AbstractByteBean<EmvTransactionRecord?>(), Serializable {
    /**
     * Method used to get the field amount
     *
     * @return the amount
     */
    /**
     * Setter for the field amount
     *
     * @param amount the amount to set
     */
    /**
     * Amount authorized (Amount need to be formated with currency)
     */
    @Data(index = 1, size = 48, format = DataFactory.BCD_FORMAT, tag = "9f02")
    var amount: Float? = null
    /**
     * Method used to get the field cyptogramData
     *
     * @return the cyptogramData
     */
    /**
     * Setter for the field cyptogramData
     *
     * @param cyptogramData the cyptogramData to set
     */
    /**
     * Cryptogram information data
     */
    @Data(index = 2, size = 8, readHexa = true, tag = "9f27")
    var cyptogramData: String? = null
    /**
     * Method used to get the field terminalCountry
     *
     * @return the terminalCountry
     */
    /**
     * Setter for the field terminalCountry
     *
     * @param terminalCountry the terminalCountry to set
     */
    /**
     * Terminal country code
     */
    @Data(index = 3, size = 16, tag = "9f1a")
    var terminalCountry: CountryCodeEnum? = null
    /**
     * Method used to get the field currency
     *
     * @return the currency
     */
    /**
     * Setter for the field currency
     *
     * @param currency the currency to set
     */
    /**
     * Currency
     */
    @Data(index = 4, size = 16, tag = "5f2a")
    var currency: CurrencyEnum? = null
    /**
     * Method used to get the field date
     *
     * @return the date
     */
    /**
     * Setter for the field date
     *
     * @param date the date to set
     */
    /**
     * Transaction date
     */
    @Data(index = 5, size = 24, dateStandard = DataFactory.BCD_DATE, format = "yyMMdd", tag = "9a")
    var date: Date? = null
    /**
     * Method used to get the field transactionType
     *
     * @return the transactionType
     */
    /**
     * Setter for the field transactionType
     *
     * @param transactionType the transactionType to set
     */
    /**
     * Transaction type (0:Payment, other:Withdrawal)
     */
    @Data(index = 6, size = 8, readHexa = true, tag = "9c")
    var transactionType: TransactionTypeEnum? = null
    /**
     * Method used to get the field time
     *
     * @return the time
     */
    /**
     * Setter for the field time
     *
     * @param time the time to set
     */
    /**
     * Transaction time
     */
    @Data(
        index = 7,
        size = 24,
        dateStandard = DataFactory.BCD_DATE,
        format = "HHmmss",
        tag = "9f21"
    )
    var time: Date? = null

    companion object {
        /**
         * Generated serial UID
         */
        private const val serialVersionUID = -7050737312961921452L
    }
}