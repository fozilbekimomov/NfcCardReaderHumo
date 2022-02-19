package uz.android.creditCardNfcReader.enums

import org.apache.commons.lang3.StringUtils
import java.util.regex.Pattern

/**
 * Class used to define all supported NFC EMV paycard. <link></link>http://en.wikipedia.org/wiki/Europay_Mastercard_Visa
 *
 */
enum class EmvCardScheme(pScheme: String, pRegex: String?, vararg pAids: String) {
    UNKNOWN("", ""),
    VISA(
        "VISA",
        "^4[0-9]{6,}$",
        "A0 00 00 00 03",
        "A0 00 00 00 03 10 10",
        "A0 00 00 00 98 08 48"
    ),
    NAB_VISA(
        "VISA",
        "^4[0-9]{6,}$",
        "A0 00 00 00 03",
        "A0 00 00 03",
        "A0 00 00 00 03 10 10",
        "A0 00 00 00 98 08 48"
    ),
    MASTER_CARD("Master card", "^5[1-5][0-9]{5,}$", "A0 00 00 00 04", "A0 00 00 00 05"),  //
    AMERICAN_EXPRESS("American express", "^3[47][0-9]{5,}$", "A0 00 00 00 25"),  //
    CB("CB", null, "A0 00 00 00 42"),  //
    LINK("LINK", null, "A0 00 00 00 29"),  //
    JCB("JCB", "^(?:2131|1800|35[0-9]{3})[0-9]{3,}$", "A0 00 00 00 65"),  //
    DANKORT("Dankort", null, "A0 00 00 01 21 10 10"),  //
    COGEBAN("CoGeBan", null, "A0 00 00 01 41 00 01"),  //
    DISCOVER("Discover", "(6011|65|64[4-9]|622)[0-9]*", "A0 00 00 01 52 30 10"),  //
    BANRISUL("Banrisul", null, "A0 00 00 01 54"),  //
    SPAN("Saudi Payments Network", null, "A0 00 00 02 28"),  //
    INTERAC("Interac", null, "A0 00 00 02 77"),  //
    ZIP("Discover Card", null, "A0 00 00 03 24"),  //
    UNIONPAY("UnionPay", "^62[0-9]{14,17}", "A0 00 00 03 33"),  //
    EAPS("Euro Alliance of Payment Schemes", null, "A0 00 00 03 59"),  //
    VERVE("Verve", null, "A0 00 00 03 71"),  //
    TENN("The Exchange Network ATM Network", null, "A0 00 00 04 39"),  //
    RUPAY("Rupay", null, "A0 00 00 05 24 10 10"),  //
    ПРО100("ПРО100", null, "A0 00 00 04 32 00 01"),  //
    ZKA("ZKA", null, "D2 76 00 00 25 45 50 01 00"),  //
    BANKAXEPT("Bankaxept", null, "D5 78 00 00 02 10 10"),  //
    BRADESCO("BRADESCO", null, "F0 00 00 00 03 00 01"), MIDLAND(
        "Midland",
        null,
        "A0 00 00 00 24 01"
    ),  //
    PBS("PBS", null, "A0 00 00 01 21 10 10"),  //
    ETRANZACT("eTranzact", null, "A0 00 00 04 54"),  //
    GOOGLE("Google", null, "A0 00 00 04 76 6C"),  //
    INTER_SWITCH("InterSwitch", null, "A0 00 00 03 71 00 01"),  //
    HUMO("HUMO", "^62[0-9]{14,17}", "A0 00 00 00 03 00 00 00");
    /**
     * Method used to get the field aid
     *
     * @return the aid
     */
    /**
     * array of Card AID or partial AID (RID)
     */
    val aid: Array<String>
    /**
     * Method used to get the field aidByte
     *
     * @return the aidByte
     */
    /**
     * array of Aid in byte
     */
    val aidByte: Array<ByteArray?>

    /**
     * Card scheme (card number IIN ranges)
     */
    private val mName: String

    /**
     * Card number pattern regex
     */
    private var pattern: Pattern? = null

    /**
     * Method used to get the field name
     *
     * @return the name
     */
    fun getmName(): String {
        return mName
    }

    companion object {
        /**
         * Get card type by AID
         *
         * @param pAid
         * card AID
         * @return CardType or null
         */
        @JvmStatic
        fun getCardTypeByAid(pAid: String?): EmvCardScheme {
            var ret = UNKNOWN
            if (pAid != null) {
                val aid = StringUtils.deleteWhitespace(pAid)
                for (scheme in values()) {
                    for (schemeAid in scheme.aid) {
                        if (aid.startsWith(StringUtils.deleteWhitespace(schemeAid))) {
                            ret = scheme
                            break
                        }
                    }
                }
            }
            return ret
        }

        /**
         * Method used to the the card type with regex
         *
         * @param pCardNumber
         * card number
         * @return the type of the card using regex
         */
        @JvmStatic
        fun getCardTypeByCardNumber(pCardNumber: String?): EmvCardScheme {
            var ret = UNKNOWN
            if (pCardNumber != null) {
                for (scheme in values()) {
                    if (scheme.pattern != null && scheme.pattern!!.matcher(
                            StringUtils.deleteWhitespace(
                                pCardNumber
                            )
                        ).matches()
                    ) {
                        ret = scheme
                        break
                    }
                }
            }
            return ret
        }
    }

    /**
     * Constructor using fields
     *
     * @param pAids
     * Card AID or RID
     * @param pScheme
     * scheme name
     * @param pRegex
     * Card regex
     */
    init {
        aid = pAids as Array<String>
        aidByte = arrayOfNulls(pAids.size)
        for (i in aid.indices) {
            aidByte[i] = uz.android.creditCardNfcReader.utils.BytesUtils.fromString(pAids[i])
        }
        mName = pScheme
        pattern = if (StringUtils.isNotBlank(pRegex)) {
            Pattern.compile(pRegex)
        } else {
            null
        }
    }
}