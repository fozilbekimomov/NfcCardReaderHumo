package uz.android.creditCardNfcReader.model.enums

/**
 * [ISO 3166-1](http://en.wikipedia.org/wiki/ISO_3166-1) country code.
 *
 *
 *
 * Enum names of this enum themselves are represented by [ISO 3166-1
 * alpha-2](http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) codes. There are instance methods to get the country name ([.getName] ), the [ISO 3166-1 alpha-3](http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3) code ([.getAlpha3]) and the [ISO 3166-1 numeric](http://en.wikipedia.org/wiki/ISO_3166-1_numeric) code ([.getNumeric]). In addition, there
 * are static methods to get a CountryCode instance that corresponds to a given alpha-2/alpha-3/numeric code.
 *
 *
 * <pre style="background-color: #EEEEEE; margin-left: 2em; margin-right: 2em; border: 1px solid black;">
 * <span style="color: darkgreen;">// EXAMPLE</span>
 *
 * CountryCode cc = CountryCode.[getByCode][.getByCode]("JP");
 *
 * <span style="color: darkgreen;">// Country name</span>
 * System.out.println("Country name = " + cc.[.getName]);                  <span style="color: darkgreen;">// "Japan"</span>
 *
 * <span style="color: darkgreen;">// ISO 3166-1 alpha-2 code</span>
 * System.out.println("ISO 3166-1 alpha-2 code = " + cc.[.getAlpha2]);     <span style="color: darkgreen;">// "JP"</span>
 *
 * <span style="color: darkgreen;">// ISO 3166-1 alpha-3 code</span>
 * System.out.println("ISO 3166-1 alpha-3 code = " + cc.[.getAlpha3]);     <span style="color: darkgreen;">// "JPN"</span>
 *
 * <span style="color: darkgreen;">// ISO 3166-1 numeric code</span>
 * System.out.println("ISO 3166-1 numeric code = " + cc.[.getNumeric]);    <span style="color: darkgreen;">// 392</span>
</pre> *
 *
 * @author Takahiko Kawasaki
 */
enum class CountryCodeEnum(
    /**
     * Get the [ISO 3166-1 alpha-2](http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) code.
     *
     * @return The [ISO 3166-1 alpha-2](http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) code.
     */
    // @formatter:on
    val alpha2: String,
    /**
     * Get the [ISO 3166-1 alpha-3](http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3) code.
     *
     * @return The [ISO 3166-1 alpha-3](http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3) code.
     */
    val alpha3: String,
    /**
     * Get the [ISO 3166-1 numeric](http://en.wikipedia.org/wiki/ISO_3166-1_numeric) code.
     *
     * @return The [ISO 3166-1 numeric](http://en.wikipedia.org/wiki/ISO_3166-1_numeric) code.
     */
    val numeric: Int
) : IKeyEnum {
    // @formatter:off
    /** [Andorra](http://en.wikipedia.org/wiki/Andorra)  */
    AD("Andorra", "AND", 16),

    /**
     * [United Arab Emirates](http://en.wikipedia.org/wiki/United_Arab_Emirates)
     */
    AE("United Arab Emirates", "ARE", 784),

    /** [Afghanistan](http://en.wikipedia.org/wiki/Afghanistan)  */
    AF("Afghanistan", "AFG", 4),

    /**
     * [Antigua and Barbuda](http://en.wikipedia.org/wiki/Antigua_and_Barbuda)
     */
    AG("Antigua and Barbuda", "ATG", 28),

    /** [Anguilla](http://en.wikipedia.org/wiki/Anguilla)  */
    AI("Anguilla", "AIA", 660),

    /** [Albania](http://en.wikipedia.org/wiki/Albania)  */
    AL("Albania", "ALB", 8),

    /** [Armenia](http://en.wikipedia.org/wiki/Armenia)  */
    AM("Armenia", "ARM", 51),

    /**
     * [Netherlands Antilles](http://en.wikipedia.org/wiki/Netherlands_Antilles)
     */
    AN("Netherlands Antilles", "ANT", 530),

    /** [Angola](http://en.wikipedia.org/wiki/Angola)  */
    AO("Angola", "AGO", 24),

    /** [Antarctica](http://en.wikipedia.org/wiki/Antarctica)  */
    AQ("Antarctica", "ATA", 10),

    /** [Argentina](http://en.wikipedia.org/wiki/Argentina)  */
    AR("Argentina", "ARG", 32),

    /** [American Samoa](http://en.wikipedia.org/wiki/American_Samoa)  */
    AS("American Samoa", "ASM", 16),

    /** [Austria](http://en.wikipedia.org/wiki/Austria)  */
    AT("Austria", "AUT", 40),

    /** [Australia](http://en.wikipedia.org/wiki/Australia)  */
    AU("Australia", "AUS", 36),

    /** [Aruba](http://en.wikipedia.org/wiki/Aruba)  */
    AW("Aruba", "ABW", 533),

    /**
     * [land Islands](http://en.wikipedia.org/wiki/%C3%85land_Islands)
     */
    AX("\u212Bland Islands", "ALA", 248),

    /** [Azerbaijan](http://en.wikipedia.org/wiki/Azerbaijan)  */
    AZ("Azerbaijan", "AZE", 31),

    /**
     * [Bosnia and Herzegovina](http://en.wikipedia.org/wiki/Bosnia_and_Herzegovina)
     */
    BA("Bosnia and Herzegovina", "BIH", 70),

    /** [Barbados](http://en.wikipedia.org/wiki/Barbados)  */
    BB("Barbados", "BRB", 52),

    /** [Bangladesh](http://en.wikipedia.org/wiki/Bangladesh)  */
    BD("Bangladesh", "BGD", 50),

    /** [Belgium](http://en.wikipedia.org/wiki/Belgium)  */
    BE("Belgium", "BEL", 56),

    /** [Burkina Faso](http://en.wikipedia.org/wiki/Burkina_Faso)  */
    BF("Burkina Faso", "BFA", 854),

    /** [Bulgaria](http://en.wikipedia.org/wiki/Bulgaria)  */
    BG("Bulgaria", "BGR", 100),

    /** [Bahrain](http://en.wikipedia.org/wiki/Bahrain)  */
    BH("Bahrain", "BHR", 48),

    /** [Burundi](http://en.wikipedia.org/wiki/Burundi)  */
    BI("Burundi", "BDI", 108),

    /** [Benin](http://en.wikipedia.org/wiki/Benin)  */
    BJ("Benin", "BEN", 204),

    /**
     * [Saint Barthlemy](http://en.wikipedia.org/wiki/Saint_Barth%C3%A9lemy)
     */
    BL("Saint Barth\u00E9lemy", "BLM", 652),

    /** [Bermuda](http://en.wikipedia.org/wiki/Bermuda)  */
    BM("Bermuda", "BMU", 60),

    /** [Brunei Darussalam](http://en.wikipedia.org/wiki/Brunei)  */
    BN("Brunei Darussalam", "BRN", 96),

    /**
     * [Plurinational State of Bolivia](http://en.wikipedia.org/wiki/Bolivia)
     */
    BO("Plurinational State of Bolivia", "BOL", 68),

    /**
     * [Bonaire, Sint Eustatius and Saba](http://en.wikipedia.org/wiki/Caribbean_Netherlands)
     */
    BQ("Bonaire, Sint Eustatius and Saba", "BES", 535),

    /** [Brazil](http://en.wikipedia.org/wiki/Brazil)  */
    BR("Brazil", "BRA", 76),

    /** [Bahamas](http://en.wikipedia.org/wiki/The_Bahamas)  */
    BS("Bahamas", "BHS", 44),

    /** [Bhutan](http://en.wikipedia.org/wiki/Bhutan)  */
    BT("Bhutan", "BTN", 64),

    /** [Bouvet Island](http://en.wikipedia.org/wiki/Bouvet_Island)  */
    BV("Bouvet Island", "BVT", 74),

    /** [Botswana](http://en.wikipedia.org/wiki/Botswana)  */
    BW("Botswana", "BWA", 72),

    /** [Belarus](http://en.wikipedia.org/wiki/Belarus)  */
    BY("Belarus", "BLR", 112),

    /** [Belize](http://en.wikipedia.org/wiki/Belize)  */
    BZ("Belize", "BLZ", 84),

    /** [Canada](http://en.wikipedia.org/wiki/Canada)  */
    CA("Canada", "CAN", 124),

    /**
     * [Cocos (Keeling) Islands](http://en.wikipedia.org/wiki/Cocos_(Keeling)_Islands)
     */
    CC("Cocos Islands", "CCK", 166),

    /**
     * [ The Democratic Republic of the Congo](http://en.wikipedia.org/wiki/Democratic_Republic_of_the_Congo)
     */
    CD("The Democratic Republic of the Congo", "COD", 180),

    /**
     * [Central African Republic](http://en.wikipedia.org/wiki/Central_African_Republic)
     */
    CF("Central African Republic", "CAF", 140),

    /** [Congo](http://en.wikipedia.org/wiki/Republic_of_the_Congo)  */
    CG("Congo", "COG", 178),

    /** [Switzerland](http://en.wikipedia.org/wiki/Switzerland)  */
    CH("Switzerland", "CHE", 756),

    /**
     * [Cte d'Ivoire](http://en.wikipedia.org/wiki/C%C3%B4te_d%27Ivoire)
     */
    CI("C\u00F4te d'Ivoire", "CIV", 384),

    /** [Cook Islands](http://en.wikipedia.org/wiki/Cook_Islands)  */
    CK("Cook Islands", "COK", 184),

    /** [Chile](http://en.wikipedia.org/wiki/Chile)  */
    CL("Chile", "CHL", 152),

    /** [Cameroon](http://en.wikipedia.org/wiki/Cameroon)  */
    CM("Cameroon", "CMR", 120),

    /** [China](http://en.wikipedia.org/wiki/China)  */
    CN("China", "CHN", 156),

    /** [Colombia](http://en.wikipedia.org/wiki/Colombia)  */
    CO("Colombia", "COL", 170),

    /** [Costa Rica](http://en.wikipedia.org/wiki/Costa_Rica)  */
    CR("Costa Rica", "CRI", 188),

    /** [Cuba](http://en.wikipedia.org/wiki/Cuba)  */
    CU("Cuba", "CUB", 192),

    /** [Cape Verde](http://en.wikipedia.org/wiki/Cape_Verde)  */
    CV("Cape Verde", "CPV", 132),

    /** [Curaao](http://en.wikipedia.org/wiki/Cura%C3%A7ao)  */
    CW("Cura/u00E7ao", "CUW", 531),

    /**
     * [Christmas Island](http://en.wikipedia.org/wiki/Christmas_Island)
     */
    CX("Christmas Island", "CXR", 162),

    /** [Cyprus](http://en.wikipedia.org/wiki/Cyprus)  */
    CY("Cyprus", "CYP", 196),

    /** [Czech Republic](http://en.wikipedia.org/wiki/Czech_Republic)  */
    CZ("Czech Republic", "CZE", 203),

    /** [Germany](http://en.wikipedia.org/wiki/Germany)  */
    DE("Germany", "DEU", 276),

    /** [Djibouti ](http://en.wikipedia.org/wiki/Djibouti)  */
    DJ("Djibouti", "DJI", 262),

    /** [Denmark](http://en.wikipedia.org/wiki/Denmark)  */
    DK("Denmark", "DNK", 208),

    /** [Dominica](http://en.wikipedia.org/wiki/Dominica)  */
    DM("Dominica", "DMA", 212),

    /**
     * [Dominican Republic](http://en.wikipedia.org/wiki/Dominican_Republic)
     */
    DO("Dominican Republic", "DOM", 214),

    /** [Algeria](http://en.wikipedia.org/wiki/Algeria)  */
    DZ("Algeria", "DZA", 12),

    /** [Ecuador](http://en.wikipedia.org/wiki/Ecuador)  */
    EC("Ecuador", "ECU", 218),

    /** [Estonia](http://en.wikipedia.org/wiki/Estonia)  */
    EE("Estonia", "EST", 233),

    /** [Egypt](http://en.wikipedia.org/wiki/Egypt)  */
    EG("Egypt", "EGY", 818),

    /** [Western Sahara](http://en.wikipedia.org/wiki/Western_Sahara)  */
    EH("Western Sahara", "ESH", 732),

    /** [Eritrea](http://en.wikipedia.org/wiki/Eritrea)  */
    ER("Eritrea", "ERI", 232),

    /** [Spain](http://en.wikipedia.org/wiki/Spain)  */
    ES("Spain", "ESP", 724),

    /** [Ethiopia](http://en.wikipedia.org/wiki/Ethiopia)  */
    ET("Ethiopia", "ETH", 231),

    /** [Finland](http://en.wikipedia.org/wiki/Finland)  */
    FI("Finland", "FIN", 246),

    /** [Fiji](http://en.wikipedia.org/wiki/Fiji)  */
    FJ("Fiji", "FJI", 242),

    /**
     * [Falkland Islands (Malvinas)](http://en.wikipedia.org/wiki/Falkland_Islands)
     */
    FK("Falkland Islands", "FLK", 238),

    /**
     * [ Federated States of Micronesia](http://en.wikipedia.org/wiki/Federated_States_of_Micronesia)
     */
    FM("Federated States of Micronesia", "FSM", 583),

    /** [Faroe Islands](http://en.wikipedia.org/wiki/Faroe_Islands)  */
    FO("Faroe Islands", "FRO", 234),

    /** [France](http://en.wikipedia.org/wiki/France)  */
    FR("France", "FRA", 250),

    /** [Gabon ](http://en.wikipedia.org/wiki/Gabon)  */
    GA("Gabon", "GAB", 266),

    /** [United Kingdom](http://en.wikipedia.org/wiki/United_Kingdom)  */
    GB("United Kingdom", "GBR", 826),

    /** [Grenada](http://en.wikipedia.org/wiki/Grenada)  */
    GD("Grenada", "GRD", 308),

    /** [Georgia](http://en.wikipedia.org/wiki/Georgia_(country))  */
    GE("Georgia", "GEO", 268),

    /** [French Guiana](http://en.wikipedia.org/wiki/French_Guiana)  */
    GF("French Guiana", "GUF", 254),

    /** [Guemsey](http://en.wikipedia.org/wiki/Guernsey)  */
    GG("Guemsey", "GGY", 831),

    /** [Ghana](http://en.wikipedia.org/wiki/Ghana)  */
    GH("Ghana", "GHA", 288),

    /** [Gibraltar](http://en.wikipedia.org/wiki/Gibraltar)  */
    GI("Gibraltar", "GIB", 292),

    /** [Greenland](http://en.wikipedia.org/wiki/Greenland)  */
    GL("Greenland", "GRL", 304),

    /** [Gambia](http://en.wikipedia.org/wiki/The_Gambia)  */
    GM("Gambia", "GMB", 270),

    /** [Guinea](http://en.wikipedia.org/wiki/Guinea)  */
    GN("Guinea", "GIN", 324),

    /** [Guadeloupe](http://en.wikipedia.org/wiki/Guadeloupe)  */
    GP("Guadeloupe", "GLP", 312),

    /**
     * [Equatorial Guinea](http://en.wikipedia.org/wiki/Equatorial_Guinea)
     */
    GQ("Equatorial Guinea", "GNQ", 226),

    /** [Greece](http://en.wikipedia.org/wiki/Greece)  */
    GR("Greece", "GRC", 300),

    /**
     * [South Georgia and the South Sandwich
 * Islands](http://en.wikipedia.org/wiki/South_Georgia_and_the_South_Sandwich_Islands)
     */
    GS("South Georgia and the South Sandwich Islands", "SGS", 239),

    /** [Guatemala](http://en.wikipedia.org/wiki/Guatemala)  */
    GT("Guatemala", "GTM", 320),

    /** [Guam](http://en.wikipedia.org/wiki/Guam)  */
    GU("Guam", "GUM", 316),

    /** [Guinea-Bissau](http://en.wikipedia.org/wiki/Guinea-Bissau)  */
    GW("Guinea-Bissau", "GNB", 624),

    /** [Guyana](http://en.wikipedia.org/wiki/Guyana)  */
    GY("Guyana", "GUY", 328),

    /** [Hong Kong](http://en.wikipedia.org/wiki/Hong_Kong)  */
    HK("Hong Kong", "HKG", 344),

    /**
     * [ Heard Island and McDonald Islands](http://en.wikipedia.org/wiki/Heard_Island_and_McDonald_Islands)
     */
    HM("Heard Island and McDonald Islands", "HMD", 334),

    /** [Honduras](http://en.wikipedia.org/wiki/Honduras)  */
    HN("Honduras", "HND", 340),

    /** [Croatia](http://en.wikipedia.org/wiki/Croatia)  */
    HR("Croatia", "HRV", 191),

    /** [Haiti](http://en.wikipedia.org/wiki/Haiti)  */
    HT("Haiti", "HTI", 332),

    /** [Hungary](http://en.wikipedia.org/wiki/Hungary)  */
    HU("Hungary", "HUN", 348),

    /** [Indonesia](http://en.wikipedia.org/wiki/Indonesia)  */
    ID("Indonesia", "IDN", 360),

    /** [Ireland](http://en.wikipedia.org/wiki/Republic_of_Ireland)  */
    IE("Ireland", "IRL", 372),

    /** [Israel](http://en.wikipedia.org/wiki/Israel)  */
    IL("Israel", "ISR", 376),

    /** [Isle of Man](http://en.wikipedia.org/wiki/Isle_of_Man)  */
    IM("Isle of Man", "IMN", 833),

    /** [India](http://en.wikipedia.org/wiki/India)  */
    IN("India", "IND", 356),

    /**
     * [ British Indian Ocean Territory](http://en.wikipedia.org/wiki/British_Indian_Ocean_Territory)
     */
    IO("British Indian Ocean Territory", "IOT", 86),

    /** [Iraq](http://en.wikipedia.org/wiki/Iraq)  */
    IQ("Iraq", "IRQ", 368),

    /** [Islamic Republic of Iran](http://en.wikipedia.org/wiki/Iran)  */
    IR("Islamic Republic of Iran", "IRN", 364),

    /** [Iceland](http://en.wikipedia.org/wiki/Iceland)  */
    IS("Iceland", "ISL", 352),

    /** [Italy](http://en.wikipedia.org/wiki/Italy)  */
    IT("Italy", "ITA", 380),

    /** [Jersey](http://en.wikipedia.org/wiki/Jersey)  */
    JE("Jersey", "JEY", 832),

    /** [Jamaica](http://en.wikipedia.org/wiki/Jamaica)  */
    JM("Jamaica", "JAM", 388),

    /** [Jordan](http://en.wikipedia.org/wiki/Jordan)  */
    JO("Jordan", "JOR", 400),

    /** [Japan](http://en.wikipedia.org/wiki/Japan)  */
    JP("Japan", "JPN", 392),

    /** [Kenya](http://en.wikipedia.org/wiki/Kenya)  */
    KE("Kenya", "KEN", 404),

    /** [Kyrgyzstan](http://en.wikipedia.org/wiki/Kyrgyzstan)  */
    KG("Kyrgyzstan", "KGZ", 417),

    /** [Cambodia](http://en.wikipedia.org/wiki/Cambodia)  */
    KH("Cambodia", "KHM", 116),

    /** [Kiribati](http://en.wikipedia.org/wiki/Kiribati)  */
    KI("Kiribati", "KIR", 296),

    /** [Comoros](http://en.wikipedia.org/wiki/Comoros)  */
    KM("Comoros", "COM", 174),

    /**
     * [Saint Kitts and Nevis](http://en.wikipedia.org/wiki/Saint_Kitts_and_Nevis)
     */
    KN("Saint Kitts and Nevis", "KNA", 659),

    /**
     * [Democratic People's Republic of Korea](http://en.wikipedia.org/wiki/North_Korea)
     */
    KP("Democratic People's Republic of Korea", "PRK", 408),

    /** [Republic of Korea](http://en.wikipedia.org/wiki/South_Korea)  */
    KR("Republic of Korea", "KOR", 410),

    /** [Kuwait](http://en.wikipedia.org/wiki/Kuwait)  */
    KW("Kuwait", "KWT", 414),

    /** [Cayman Islands](http://en.wikipedia.org/wiki/Cayman_Islands)  */
    KY("Cayman Islands", "CYM", 136),

    /** [Kazakhstan](http://en.wikipedia.org/wiki/Kazakhstan)  */
    KZ("Kazakhstan", "KAZ", 398),

    /**
     * [Lao People's Democratic Republic](http://en.wikipedia.org/wiki/Laos)
     */
    LA("Lao People's Democratic Republic", "LAO", 418),

    /** [Lebanon](http://en.wikipedia.org/wiki/Lebanon)  */
    LB("Lebanon", "LBN", 422),

    /** [Saint Lucia](http://en.wikipedia.org/wiki/Saint_Lucia)  */
    LC("Saint Lucia", "LCA", 662),

    /** [Liechtenstein](http://en.wikipedia.org/wiki/Liechtenstein)  */
    LI("Liechtenstein", "LIE", 438),

    /** [Sri Lanka](http://en.wikipedia.org/wiki/Sri_Lanka)  */
    LK("Sri Lanka", "LKA", 144),

    /** [Liberia](http://en.wikipedia.org/wiki/Liberia)  */
    LR("Liberia", "LBR", 430),

    /** [Lesotho](http://en.wikipedia.org/wiki/Lesotho)  */
    LS("Lesotho", "LSO", 426),

    /** [Lithuania](http://en.wikipedia.org/wiki/Lithuania)  */
    LT("Lithuania", "LTU", 440),

    /** [Luxembourg](http://en.wikipedia.org/wiki/Luxembourg)  */
    LU("Luxembourg", "LUX", 442),

    /** [Latvia](http://en.wikipedia.org/wiki/Latvia)  */
    LV("Latvia", "LVA", 428),

    /** [Libya](http://en.wikipedia.org/wiki/Libya)  */
    LY("Libya", "LBY", 434),

    /** [Morocco](http://en.wikipedia.org/wiki/Morocco)  */
    MA("Morocco", "MAR", 504),

    /** [Monaco](http://en.wikipedia.org/wiki/Monaco)  */
    MC("Monaco", "MCO", 492),

    /** [Republic of Moldova](http://en.wikipedia.org/wiki/Moldova)  */
    MD("Republic of Moldova", "MDA", 498),

    /** [Montenegro](http://en.wikipedia.org/wiki/Montenegro)  */
    ME("Montenegro", "MNE", 499),

    /**
     * [Saint Martin (French part)](http://en.wikipedia.org/wiki/Collectivity_of_Saint_Martin)
     */
    MF("Saint Martin", "MAF", 663),

    /** [Madagascar](http://en.wikipedia.org/wiki/Madagascar)  */
    MG("Madagascar", "MDG", 450),

    /**
     * [Marshall Islands](http://en.wikipedia.org/wiki/Marshall_Islands)
     */
    MH("Marshall Islands", "MHL", 584),

    /**
     * [The former Yugoslav Republic of Macedonia](http://en.wikipedia.org/wiki/Republic_of_Macedonia)
     */
    MK("The former Yugoslav Republic of Macedonia", "MKD", 807),

    /** [Mali](http://en.wikipedia.org/wiki/Mali)  */
    ML("Mali", "MLI", 466),

    /** [Myanmar](http://en.wikipedia.org/wiki/Myanmar)  */
    MM("Myanmar", "MMR", 104),

    /** [Mongolia](http://en.wikipedia.org/wiki/Mongolia)  */
    MN("Mongolia", "MNG", 496),

    /** [Macao](http://en.wikipedia.org/wiki/Macau)  */
    MO("Macao", "MCO", 492),

    /**
     * [Northern Mariana Islands](http://en.wikipedia.org/wiki/Northern_Mariana_Islands)
     */
    MP("Northern Mariana Islands", "MNP", 580),

    /** [Martinique](http://en.wikipedia.org/wiki/Martinique)  */
    MQ("Martinique", "MTQ", 474),

    /** [Mauritania](http://en.wikipedia.org/wiki/Mauritania)  */
    MR("Mauritania", "MRT", 478),

    /** [Montserrat](http://en.wikipedia.org/wiki/Montserrat)  */
    MS("Montserrat", "MSR", 500),

    /** [Malta](http://en.wikipedia.org/wiki/Malta)  */
    MT("Malta", "MLT", 470),

    /** [Mauritius](http://en.wikipedia.org/wiki/Mauritius)  */
    MU("Mauritius", "MUS", 480),

    /** [Maldives](http://en.wikipedia.org/wiki/Maldives)  */
    MV("Maldives", "MDV", 462),

    /** [Malawi](http://en.wikipedia.org/wiki/Malawi)  */
    MW("Malawi", "MWI", 454),

    /** [Mexico](http://en.wikipedia.org/wiki/Mexico)  */
    MX("Mexico", "MEX", 484),

    /** [Malaysia](http://en.wikipedia.org/wiki/Malaysia)  */
    MY("Malaysia", "MYS", 458),

    /** [Mozambique](http://en.wikipedia.org/wiki/Mozambique)  */
    MZ("Mozambique", "MOZ", 508),

    /** [Namibia](http://en.wikipedia.org/wiki/Namibia)  */
    NA("Namibia", "NAM", 516),

    /** [New Caledonia](http://en.wikipedia.org/wiki/New_Caledonia)  */
    NC("New Caledonia", "NCL", 540),

    /** [Niger](http://en.wikipedia.org/wiki/Niger)  */
    NE("Niger", "NER", 562),

    /** [Norfolk Island](http://en.wikipedia.org/wiki/Norfolk_Island)  */
    NF("Norfolk Island", "NFK", 574),

    /** [Nigeria](http://en.wikipedia.org/wiki/Nigeria)  */
    NG("Nigeria", "NGA", 566),

    /** [Nicaragua](http://en.wikipedia.org/wiki/Nicaragua)  */
    NI("Nicaragua", "NIC", 558),

    /** [Netherlands](http://en.wikipedia.org/wiki/Netherlands)  */
    NL("Netherlands", "NLD", 528),

    /** [Norway](http://en.wikipedia.org/wiki/Norway)  */
    NO("Norway", "NOR", 578),

    /** [Nepal](http://en.wikipedia.org/wiki/Nepal)  */
    NP("Nepal", "NPL", 524),

    /** [Nauru](http://en.wikipedia.org/wiki/Nauru)  */
    NR("Nauru", "NRU", 520),

    /** [Niue](http://en.wikipedia.org/wiki/Niue)  */
    NU("Niue", "NIU", 570),

    /** [New Zealand](http://en.wikipedia.org/wiki/New_Zealand)  */
    NZ("New Zealand", "NZL", 554),

    /** [Oman](http://en.wikipedia.org/wiki/Oman"")  */
    OM("Oman", "OMN", 512),

    /** [Panama](http://en.wikipedia.org/wiki/Panama)  */
    PA("Panama", "PAN", 591),

    /** [Peru](http://en.wikipedia.org/wiki/Peru)  */
    PE("Peru", "PER", 604),

    /**
     * [French Polynesia](http://en.wikipedia.org/wiki/French_Polynesia)
     */
    PF("French Polynesia", "PYF", 258),

    /**
     * [Papua New Guinea](http://en.wikipedia.org/wiki/Papua_New_Guinea)
     */
    PG("Papua New Guinea", "PNG", 598),

    /** [Philippines](http://en.wikipedia.org/wiki/Philippines)  */
    PH("Philippines", "PHL", 608),

    /** [Pakistan](http://en.wikipedia.org/wiki/Pakistan)  */
    PK("Pakistan", "PAK", 586),

    /** [Poland](http://en.wikipedia.org/wiki/Poland)  */
    PL("Poland", "POL", 616),

    /**
     * [Saint Pierre and Miquelon](http://en.wikipedia.org/wiki/Saint_Pierre_and_Miquelon)
     */
    PM("Saint Pierre and Miquelon", "SPM", 666),

    /** [Pitcairn](http://en.wikipedia.org/wiki/Pitcairn_Islands)  */
    PN("Pitcairn", "PCN", 612),

    /** [Puerto Rico](http://en.wikipedia.org/wiki/Puerto_Rico)  */
    PR("Puerto Rico", "PRI", 630),

    /**
     * [Occupied Palestinian Territory](http://en.wikipedia.org/wiki/Palestinian_territories)
     */
    PS("Occupied Palestinian Territory", "PSE", 275),

    /** [Portugal](http://en.wikipedia.org/wiki/Portugal)  */
    PT("Portugal", "PRT", 620),

    /** [Palau](http://en.wikipedia.org/wiki/Palau)  */
    PW("Palau", "PLW", 585),

    /** [Paraguay](http://en.wikipedia.org/wiki/Paraguay)  */
    PY("Paraguay", "PRY", 600),

    /** [Qatar](http://en.wikipedia.org/wiki/Qatar)  */
    QA("Qatar", "QAT", 634),

    /** [Runion](http://en.wikipedia.org/wiki/R%C3%A9union)  */
    RE("R\u00E9union", "REU", 638),

    /** [Romania](http://en.wikipedia.org/wiki/Romania)  */
    RO("Romania", "ROU", 642),

    /** [Serbia](http://en.wikipedia.org/wiki/Serbia)  */
    RS("Serbia", "SRB", 688),

    /** [Russian Federation](http://en.wikipedia.org/wiki/Russia)  */
    RU("Russian Federation", "RUS", 643),

    /** [Rwanda](http://en.wikipedia.org/wiki/Rwanda)  */
    RW("Rwanda", "RWA", 646),

    /** [Saudi Arabia](http://en.wikipedia.org/wiki/Saudi_Arabia)  */
    SA("Saudi Arabia", "SAU", 682),

    /**
     * [Solomon Islands](http://en.wikipedia.org/wiki/Solomon_Islands)
     */
    SB("Solomon Islands", "SLB", 90),

    /** [Seychelles](http://en.wikipedia.org/wiki/Seychelles)  */
    SC("Seychelles", "SYC", 690),

    /** [Sudan](http://en.wikipedia.org/wiki/Sudan)  */
    SD("Sudan", "SDN", 729),

    /** [Sweden](http://en.wikipedia.org/wiki/Sweden)  */
    SE("Sweden", "SWE", 752),

    /** [Singapore](http://en.wikipedia.org/wiki/Singapore)  */
    SG("Singapore", "SGP", 702),

    /**
     * [Saint Helena, Ascension and Tristan
 * da Cunha](http://en.wikipedia.org/wiki/Saint_Helena,_Ascension_and_Tristan_da_Cunha)
     */
    SH("Saint Helena, Ascension and Tristan da Cunha", "SHN", 654),

    /** [Slovenia](http://en.wikipedia.org/wiki/Slovenia)  */
    SI("Slovenia", "SVN", 705),

    /**
     * [Svalbard and Jan Mayen](http://en.wikipedia.org/wiki/Svalbard_and_Jan_Mayen)
     */
    SJ("Svalbard and Jan Mayen", "SJM", 744),

    /** [Slovakia](http://en.wikipedia.org/wiki/Slovakia)  */
    SK("Slovakia", "SVK", 703),

    /** [Sierra Leone](http://en.wikipedia.org/wiki/Sierra_Leone)  */
    SL("Sierra Leone", "SLE", 694),

    /** [San Marino](http://en.wikipedia.org/wiki/San_Marino)  */
    SM("San Marino", "SMR", 674),

    /** [Senegal](http://en.wikipedia.org/wiki/Senegal)  */
    SN("Senegal", "SEN", 686),

    /** [Somalia](http://en.wikipedia.org/wiki/Somalia)  */
    SO("Somalia", "SOM", 706),

    /** [Suriname](http://en.wikipedia.org/wiki/Suriname)  */
    SR("Suriname", "SUR", 740),

    /** [South Sudan](http://en.wikipedia.org/wiki/South_Sudan)  */
    SS("South Sudan", "SSD", 728),

    /**
     * [Sao Tome and Principe](http://en.wikipedia.org/wiki/S%C3%A3o_Tom%C3%A9_and_Pr%C3%ADncipe)
     */
    ST("Sao Tome and Principe", "STP", 678),

    /** [El Salvador](http://en.wikipedia.org/wiki/El_Salvador)  */
    SV("El Salvador", "SLV", 222),

    /**
     * [Sint Maarten (Dutch part)](http://en.wikipedia.org/wiki/Sint_Maarten)
     */
    SX("Sint Maarten", "SXM", 534),

    /** [Syrian Arab Republic](http://en.wikipedia.org/wiki/Syria)  */
    SY("Syrian Arab Republic", "SYR", 760),

    /** [Swaziland](http://en.wikipedia.org/wiki/Swaziland)  */
    SZ("Swaziland", "SWZ", 748),

    /**
     * [Turks and Caicos Islands](http://en.wikipedia.org/wiki/Turks_and_Caicos_Islands)
     */
    TC("Turks and Caicos Islands", "TCA", 796),

    /** [Chad](http://en.wikipedia.org/wiki/Chad)  */
    TD("Chad", "TCD", 148),

    /**
     * [French Southern Territories](http://en.wikipedia.org/wiki/French_Southern_and_Antarctic_Lands)
     */
    TF("French Southern Territories", "ATF", 260),

    /** [Togo](http://en.wikipedia.org/wiki/Togo)  */
    TG("Togo", "TGO", 768),

    /** [Thailand](http://en.wikipedia.org/wiki/Thailand)  */
    TH("Thailand", "THA", 764),

    /** [Tajikistan](http://en.wikipedia.org/wiki/Tajikistan)  */
    TJ("Tajikistan", "TJK", 762),

    /** [Tokelau](http://en.wikipedia.org/wiki/Tokelau)  */
    TK("Tokelau", "TKL", 772),

    /** [Timor-Leste](http://en.wikipedia.org/wiki/East_Timor)  */
    TL("Timor-Leste", "TLS", 626),

    /** [Turkmenistan](http://en.wikipedia.org/wiki/Turkmenistan)  */
    TM("Turkmenistan", "TKM", 795),

    /** [Tunisia](http://en.wikipedia.org/wiki/Tunisia)  */
    TN("Tunisia", "TUN", 788),

    /** [Tonga](http://en.wikipedia.org/wiki/Tonga)  */
    TO("Tonga", "TON", 776),

    /** [Turkey](http://en.wikipedia.org/wiki/Turkey)  */
    TR("Turkey", "TUR", 792),

    /**
     * [Trinidad and Tobago](http://en.wikipedia.org/wiki/Trinidad_and_Tobago)
     */
    TT("Trinidad and Tobago", "TTO", 780),

    /** [Tuvalu](http://en.wikipedia.org/wiki/Tuvalu)  */
    TV("Tuvalu", "TUV", 798),

    /**
     * [Taiwan, Province of China](http://en.wikipedia.org/wiki/Taiwan)
     */
    TW("Taiwan, Province of China", "TWN", 158),

    /**
     * [United Republic of Tanzania](http://en.wikipedia.org/wiki/Tanzania)
     */
    TZ("United Republic of Tanzania", "TZA", 834),

    /** [Ukraine](http://en.wikipedia.org/wiki/Ukraine)  */
    UA("Ukraine", "UKR", 804),

    /** [Uganda](http://en.wikipedia.org/wiki/Uganda)  */
    UG("Uganda", "UGA", 800),

    /**
     * [United States Minor Outlying Islands](http://en.wikipedia.org/wiki/United_States_Minor_Outlying_Islands)
     */
    UM("United States Minor Outlying Islands", "UMI", 581),

    /** [United States](http://en.wikipedia.org/wiki/United_States)  */
    US("United States", "USA", 840),

    /** [Uruguay](http://en.wikipedia.org/wiki/Uruguay)  */
    UY("Uruguay", "URY", 858),

    /** [Uzbekistan](http://en.wikipedia.org/wiki/Uzbekistan)  */
    UZ("Uzbekistan", "UZB", 860),

    /**
     * [Holy See (Vatican City State)](http://en.wikipedia.org/wiki/Vatican_City)
     */
    VA("Holy See", "VAT", 336),

    /**
     * [ Saint Vincent and the Grenadines](http://en.wikipedia.org/wiki/Saint_Vincent_and_the_Grenadines)
     */
    VC("Saint Vincent and the Grenadines", "VCT", 670),

    /**
     * [Bolivarian Republic of Venezuela](http://en.wikipedia.org/wiki/Venezuela)
     */
    VE("Bolivarian Republic of Venezuela", "VEN", 862),

    /**
     * [British Virgin Islands](http://en.wikipedia.org/wiki/British_Virgin_Islands)
     */
    VG("British Virgin Islands", "VGB", 92),

    /**
     * [Virgin Islands, U.S.](http://en.wikipedia.org/wiki/United_States_Virgin_Islands)
     */
    VI("Virgin Islands, U.S.", "VIR", 850),

    /** [Viet Nam](http://en.wikipedia.org/wiki/Vietnam)  */
    VN("Viet Nam", "VNM", 704),

    /** [Vanuatu](http://en.wikipedia.org/wiki/Vanuatu)  */
    VU("Vanuatu", "VUT", 548),

    /**
     * [Wallis and Futuna](http://en.wikipedia.org/wiki/Wallis_and_Futuna)
     */
    WF("Wallis and Futuna", "WLF", 876),

    /** [Samoa](http://en.wikipedia.org/wiki/Samoa)  */
    WS("Samoa", "WSM", 882),

    /** [Yemen](http://en.wikipedia.org/wiki/Yemen)  */
    YE("Yemen", "YEM", 887),

    /** [Mayotte](http://en.wikipedia.org/wiki/Mayotte)  */
    YT("Mayotte", "MYT", 175),

    /** [South Africa](http://en.wikipedia.org/wiki/South_Africa)  */
    ZA("South Africa", "ZAF", 710),

    /** [Zambia](http://en.wikipedia.org/wiki/Zambia)  */
    ZM("Zambia", "ZMB", 894),

    /** [Zimbabwe](http://en.wikipedia.org/wiki/Zimbabwe)  */
    ZW("Zimbabwe", "ZWE", 716);

    override val key: Int
        get() = numeric
}