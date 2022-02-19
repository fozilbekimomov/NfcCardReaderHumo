package uz.android.creditCardNfcReader.utils

import fr.devnied.bitlib.BytesUtils
import org.slf4j.LoggerFactory
import uz.android.creditCardNfcReader.enums.SwEnum
import uz.android.creditCardNfcReader.enums.SwEnum.Companion.getSW
import java.util.*

/**
 * Method used to manipulate response from APDU command
 */
object ResponseUtils {
    /**
     * Class logger
     */
    private val LOGGER = LoggerFactory.getLogger(ResponseUtils::class.java)

    /**
     * Method used to check if the last command return SW1SW2 == 9000
     *
     * @param pByte response to the last command
     * @return true if the status is 9000 false otherwise
     */
    @JvmStatic
    fun isSucceed(pByte: ByteArray?): Boolean {
        return isEquals(pByte, SwEnum.SW_9000)
    }

    /**
     * Method used to check equality with the last command return SW1SW2 == pEnum
     *
     * @param pByte response to the last command
     * @param pEnum response to check
     * @return true if the response of the last command is equals to pEnum
     */
    @JvmStatic
    fun isEquals(pByte: ByteArray?, pEnum: SwEnum): Boolean {
        val anEnum = getSW(pByte)
        if (LOGGER.isDebugEnabled && pByte != null) {
            val ooo = Arrays.copyOfRange(pByte, pByte.size - 2, pByte.size)
            val q = BytesUtils.bytesToStringNoSpace(ooo)
            LOGGER.debug(
                "Response Status <"
                        + q + "> : "
                        + (anEnum?.detail ?: "Unknow")
            )
        }
        return anEnum != null && anEnum === pEnum
    }
}