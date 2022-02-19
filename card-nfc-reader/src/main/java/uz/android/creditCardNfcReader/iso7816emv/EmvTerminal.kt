package uz.android.creditCardNfcReader.iso7816emv

import org.apache.commons.lang3.StringUtils
import uz.android.creditCardNfcReader.model.enums.CountryCodeEnum
import uz.android.creditCardNfcReader.model.enums.CurrencyEnum
import uz.android.creditCardNfcReader.model.enums.TransactionTypeEnum
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.util.*

/**
 * Factory to create Tag value
 */
object EmvTerminal {
    /**
     * Random
     */
    private val random = SecureRandom()

    /**
     * Method used to construct value from tag and length
     *
     * @param pTagAndLength
     * tag and length value
     * @return tag value in byte
     */
    @JvmStatic
    fun constructValue(pTagAndLength: TagAndLength): ByteArray {
        val ret = ByteArray(pTagAndLength.length)
        var value: ByteArray? = null
        if (pTagAndLength.tag === EmvTags.TERMINAL_TRANSACTION_QUALIFIERS) {
            val terminalQual = TerminalTransactionQualifiers()
            terminalQual.setContactlessEMVmodeSupported(true)
            terminalQual.setReaderIsOfflineOnly(true)
            value = terminalQual.bytes
        } else if (pTagAndLength.tag === EmvTags.TERMINAL_COUNTRY_CODE) {
            value = uz.android.creditCardNfcReader.utils.BytesUtils.fromString(
                StringUtils.leftPad(
                    CountryCodeEnum.FR.numeric.toString(), pTagAndLength.length * 2,
                    "0"
                )
            )
        } else if (pTagAndLength.tag === EmvTags.TRANSACTION_CURRENCY_CODE) {
            value = uz.android.creditCardNfcReader.utils.BytesUtils.fromString(
                StringUtils.leftPad(
                    CurrencyEnum.EUR.getISOCodeNumeric().toString(),
                    pTagAndLength.length * 2, "0"
                )
            )
        } else if (pTagAndLength.tag === EmvTags.TRANSACTION_DATE) {
            val sdf = SimpleDateFormat("yyMMdd")
            value = uz.android.creditCardNfcReader.utils.BytesUtils.fromString(sdf.format(Date()))
        } else if (pTagAndLength.tag === EmvTags.TRANSACTION_TYPE) {
            value = byteArrayOf(TransactionTypeEnum.PURCHASE.key.toByte())
        } else if (pTagAndLength.tag === EmvTags.AMOUNT_AUTHORISED_NUMERIC) {
            value = uz.android.creditCardNfcReader.utils.BytesUtils.fromString("00")
        } else if (pTagAndLength.tag === EmvTags.TERMINAL_TYPE) {
            value = byteArrayOf(0x22)
        } else if (pTagAndLength.tag === EmvTags.TERMINAL_CAPABILITIES) {
            value = byteArrayOf(0xE0.toByte(), 0xA0.toByte(), 0x00)
        } else if (pTagAndLength.tag === EmvTags.ADDITIONAL_TERMINAL_CAPABILITIES) {
            value = byteArrayOf(0x8e.toByte(), 0.toByte(), 0xb0.toByte(), 0x50, 0x05)
        } else if (pTagAndLength.tag === EmvTags.DS_REQUESTED_OPERATOR_ID) {
            value = uz.android.creditCardNfcReader.utils.BytesUtils.fromString("7345123215904501")
        } else if (pTagAndLength.tag === EmvTags.UNPREDICTABLE_NUMBER) {
            random.nextBytes(ret)
        }
        if (value != null) {
            System.arraycopy(value, 0, ret, 0, Math.min(value.size, ret.size))
        }
        return ret
    }
}