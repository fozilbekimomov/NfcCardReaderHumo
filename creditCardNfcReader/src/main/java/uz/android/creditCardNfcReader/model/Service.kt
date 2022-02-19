package uz.android.creditCardNfcReader.model
//uz.android.creditCardNfcReader
import fr.devnied.bitlib.BitUtils
import org.apache.commons.lang3.StringUtils
import uz.android.creditCardNfcReader.model.enums.ServiceCode1Enum
import uz.android.creditCardNfcReader.model.enums.ServiceCode2Enum
import uz.android.creditCardNfcReader.model.enums.ServiceCode3Enum
import uz.android.creditCardNfcReader.utils.EnumUtils

/**
 * Track 2 service
 *
 */
class Service(pData: String?) : AbstractData() {
    /**
     * Method used to get the field serviceCode1
     *
     * @return the serviceCode1
     */
    /**
     * Service code 1
     */
    var serviceCode1: ServiceCode1Enum? = null
    /**
     * Method used to get the field serviceCode2
     *
     * @return the serviceCode2
     */
    /**
     * Service code 2
     */
    var serviceCode2: ServiceCode2Enum? = null
    /**
     * Method used to get the field serviceCode3
     *
     * @return the serviceCode3
     */
    /**
     * Service code 3
     */
    var serviceCode3: ServiceCode3Enum? = null

    companion object {
        /**
         * Generated serial UID
         */
        private const val serialVersionUID = 5154895810563519768L
    }

    /**
     * Constructor with service bytes array parameter
     *
     * @param pData
     * service as byte array
     */
    init {
        if (pData != null && pData.length == 3) {
            val bit = BitUtils(
                uz.android.creditCardNfcReader.utils.BytesUtils.fromString(
                    StringUtils.rightPad(
                        pData,
                        4,
                        "0"
                    )
                )
            )
            serviceCode1 = EnumUtils.getValue(bit.getNextInteger(4), ServiceCode1Enum::class.java)
            serviceCode2 = EnumUtils.getValue(bit.getNextInteger(4), ServiceCode2Enum::class.java)
            serviceCode3 = EnumUtils.getValue(bit.getNextInteger(4), ServiceCode3Enum::class.java)
        }
    }
}