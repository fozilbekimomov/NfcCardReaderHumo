package uz.android.creditCardNfcReader.model.enums

/**
 * Service code, position 2 values.
 */
enum class ServiceCode3Enum(
    override val key: Int,
    /**
     * Gets the allowed services.
     *
     * @return Allowed services.
     */
    val allowedServices: String,
    /**
     * Gets the the PIN requirements.
     *
     * @return PIN requirements.
     */
    val pinRequirements: String
) : IKeyEnum {
    NO_RESTRICTION_PIN_REQUIRED(0, "No restrictions", "PIN required"), NO_RESTRICTION(
        1,
        "No restrictions",
        "None"
    ),
    GOODS_SERVICES(2, "Goods and services only", "None"), ATM_ONLY(
        3,
        "ATM only",
        "PIN required"
    ),
    CASH_ONLY(4, "Cash only", "None"), GOODS_SERVICES_PIN_REQUIRED(
        5,
        "Goods and services only",
        "PIN required"
    ),
    NO_RESTRICTION_PIN_IF_PED(
        6,
        "No restrictions",
        "Prompt for PIN if PED present"
    ),
    GOODS_SERVICES_PIN_IF_PED(7, "Goods and services only", "Prompt for PIN if PED present");

}