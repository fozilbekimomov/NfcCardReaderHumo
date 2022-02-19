package uz.android.creditCardNfcReader.utils

import org.slf4j.LoggerFactory
import uz.android.creditCardNfcReader.model.enums.IKeyEnum

/**
 * Utils class which provided methods to manipulate Enum
 */
object EnumUtils {
    /**
     * Class logger
     */
    private val LOGGER = LoggerFactory.getLogger(EnumUtils::class.java)

    /**
     * Get the value of and enum from his key
     *
     * @param pKey   key to find
     * @param pClass Enum class
     * @return Enum instance of the specified key or null otherwise
     */
    fun <T : IKeyEnum?> getValue(pKey: Int, pClass: Class<T>): T? {
        for (keyEnum in pClass.enumConstants) {
            if (keyEnum?.key == pKey) {
                return keyEnum
            }
        }
        LOGGER.error("Unknow value:" + pKey + " for Enum:" + pClass.name)
        return null
    }
}