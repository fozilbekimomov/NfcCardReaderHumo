package uz.android.creditCardNfcReader.model.enums

/**
 * Service code, position 1 values.
 */
enum class ServiceCode1Enum
/**
 * Constructor using fields
 *
 * @param value
 * @param interchange
 * @param technology
 */(
    override val key: Int,
    /**
     * Method used to get the field interchange
     *
     * @return the interchange
     */
    val interchange: String,
    /**
     * Method used to get the field technology
     *
     * @return the technology
     */
    val technology: String
) : IKeyEnum {
    INTERNATIONNAL(1, "International interchange", "None"), INTERNATIONNAL_ICC(
        2,
        "International interchange",
        "Integrated circuit card"
    ),
    NATIONAL(5, "National interchange", "None"), NATIONAL_ICC(
        6,
        "National interchange",
        "Integrated circuit card"
    ),
    PRIVATE(7, "Private", "None");

}