package uz.android.creditCardNfcReader.model.enums

/**
 * Service code, position 2 values.
 */
enum class ServiceCode2Enum(
    override val key: Int,
    /**
     * Gets the authorization processing rules.
     *
     * @return Authorization processing rules.
     */
    val authorizationProcessing: String
) : IKeyEnum {
    NORMAL(0, "Normal"), BY_ISSUER(2, "By issuer"), BY_ISSUER_WIHOUT_BI_AGREEMENT(
        4,
        "By issuer unless explicit bilateral agreement applies"
    );

}