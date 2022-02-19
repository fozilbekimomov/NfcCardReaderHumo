package uz.android.creditCardNfcReader.iso7816emv

import uz.android.creditCardNfcReader.enums.TagValueTypeEnum
import uz.android.creditCardNfcReader.iso7816emv.ByteArrayWrapper.Companion.wrapperAround
import uz.android.creditCardNfcReader.iso7816emv.impl.TagImpl
import java.lang.reflect.Field

/**
 * http://www.emvlab.org/emvtags/all/
 *
 *
 * The coding of primitive context-specific class data objects in the ranges '80' to '9E' and '9F00' to '9F4F' is reserved for EMV
 * specification. The coding of primitive context-specific class data objects in the range '9F50' to '9F7F' is reserved for the
 * payment systems.
 *
 */
object EmvTags {
    private val tags = LinkedHashMap<ByteArrayWrapper, ITag>()

    // @formatter:off
    // One byte tags
    // 7816-4 Interindustry data object for tag allocation authority
    val UNIVERSAL_TAG_FOR_OID: ITag =
        TagImpl("06", TagValueTypeEnum.BINARY, "Object Identifier (OID)", "Universal tag for OID")
    val COUNTRY_CODE: ITag = TagImpl(
        "41",
        TagValueTypeEnum.NUMERIC,
        "Country Code",
        "Country code (encoding specified in ISO 3166-1) and optional national data"
    )
    val ISSUER_IDENTIFICATION_NUMBER: ITag = TagImpl(
        "42",
        TagValueTypeEnum.NUMERIC,
        "Issuer Identification Number (IIN)",
        "The number that identifies the major industry and the card issuer and that forms the first part of the Primary Account Number (PAN)"
    )

    // 7816-4 Interindustry data objects for application identification and selection
    @JvmField
    val AID_CARD: ITag = TagImpl(
        "4f",
        TagValueTypeEnum.BINARY,
        "Application Identifier (AID) - card",
        "Identifies the application as described in ISO/IEC 7816-5"
    )

    @JvmField
    val APPLICATION_LABEL: ITag = TagImpl(
        "50",
        TagValueTypeEnum.TEXT,
        "Application Label",
        "Mnemonic associated with the AID according to ISO/IEC 7816-5"
    )
    val PATH: ITag =
        TagImpl("51", TagValueTypeEnum.BINARY, "File reference data element", "ISO-7816 Path")
    val COMMAND_APDU: ITag = TagImpl("52", TagValueTypeEnum.BINARY, "Command APDU", "")
    val DISCRETIONARY_DATA_OR_TEMPLATE: ITag =
        TagImpl("53", TagValueTypeEnum.BINARY, "Discretionary data (or template)", "")
    val APPLICATION_TEMPLATE: ITag = TagImpl(
        "61",
        TagValueTypeEnum.BINARY,
        "Application Template",
        "Contains one or more data objects relevant to an application directory entry according to ISO/IEC 7816-5"
    )
    val FCI_TEMPLATE: ITag = TagImpl(
        "6f",
        TagValueTypeEnum.BINARY,
        "File Control Information (FCI) Template",
        "Set of file control parameters and file management data (according to ISO/IEC 7816-4)"
    )
    val DD_TEMPLATE: ITag = TagImpl(
        "73",
        TagValueTypeEnum.BINARY,
        "Directory Discretionary Template",
        "Issuer discretionary part of the directory according to ISO/IEC 7816-5"
    )

    @JvmField
    val DEDICATED_FILE_NAME: ITag = TagImpl(
        "84",
        TagValueTypeEnum.BINARY,
        "Dedicated File (DF) Name",
        "Identifies the name of the DF as described in ISO/IEC 7816-4"
    )

    @JvmField
    val SFI: ITag = TagImpl(
        "88",
        TagValueTypeEnum.BINARY,
        "Short File Identifier (SFI)",
        "Identifies the SFI to be used in the commands related to a given AEF or DDF. The SFI data object is a binary field with the three high order bits set to zero"
    )
    val FCI_PROPRIETARY_TEMPLATE: ITag = TagImpl(
        "a5",
        TagValueTypeEnum.BINARY,
        "File Control Information (FCI) Proprietary Template",
        "Identifies the data object proprietary to this specification in the FCI template according to ISO/IEC 7816-4"
    )
    val ISSUER_URL: ITag = TagImpl(
        "5f50",
        TagValueTypeEnum.TEXT,
        "Issuer URL",
        "The URL provides the location of the Issuerâ€™s Library Server on the Internet"
    )

    // EMV
    @JvmField
    val TRACK_2_EQV_DATA: ITag = TagImpl(
        "57",
        TagValueTypeEnum.BINARY,
        "Track 2 Equivalent Data",
        "Contains the data elements of track 2 according to ISO/IEC 7813, excluding start sentinel, end sentinel, and Longitudinal Redundancy Check (LRC)"
    )
    val PAN: ITag = TagImpl(
        "5a",
        TagValueTypeEnum.NUMERIC,
        "Application Primary Account Number (PAN)",
        "Valid cardholder account number"
    )
    val RECORD_TEMPLATE: ITag = TagImpl(
        "70",
        TagValueTypeEnum.BINARY,
        "Record Template (EMV Proprietary)",
        "Template proprietary to the EMV specification"
    )
    val ISSUER_SCRIPT_TEMPLATE_1: ITag = TagImpl(
        "71",
        TagValueTypeEnum.BINARY,
        "Issuer Script Template 1",
        "Contains proprietary issuer data for transmission to the ICC before the second GENERATE AC command"
    )
    val ISSUER_SCRIPT_TEMPLATE_2: ITag = TagImpl(
        "72",
        TagValueTypeEnum.BINARY,
        "Issuer Script Template 2",
        "Contains proprietary issuer data for transmission to the ICC after the second GENERATE AC command"
    )
    val RESPONSE_MESSAGE_TEMPLATE_2: ITag = TagImpl(
        "77",
        TagValueTypeEnum.BINARY,
        "Response Message Template Format 2",
        "Contains the data objects (with tags and lengths) returned by the ICC in response to a command"
    )

    @JvmField
    val RESPONSE_MESSAGE_TEMPLATE_1: ITag = TagImpl(
        "80",
        TagValueTypeEnum.BINARY,
        "Response Message Template Format 1",
        "Contains the data objects (without tags and lengths) returned by the ICC in response to a command"
    )
    val AMOUNT_AUTHORISED_BINARY: ITag = TagImpl(
        "81",
        TagValueTypeEnum.BINARY,
        "Amount, Authorised (Binary)",
        "Authorised amount of the transaction (excluding adjustments)"
    )
    val APPLICATION_INTERCHANGE_PROFILE: ITag = TagImpl(
        "82",
        TagValueTypeEnum.BINARY,
        "Application Interchange Profile",
        "Indicates the capabilities of the card to support specific functions in the application"
    )

    @JvmField
    val COMMAND_TEMPLATE: ITag = TagImpl(
        "83",
        TagValueTypeEnum.BINARY,
        "Command Template",
        "Identifies the data field of a command message"
    )
    val ISSUER_SCRIPT_COMMAND: ITag = TagImpl(
        "86",
        TagValueTypeEnum.BINARY,
        "Issuer Script Command",
        "Contains a command for transmission to the ICC"
    )
    val APPLICATION_PRIORITY_INDICATOR: ITag = TagImpl(
        "87",
        TagValueTypeEnum.BINARY,
        "Application Priority Indicator",
        "Indicates the priority of a given application or group of applications in a directory"
    )
    val AUTHORISATION_CODE: ITag = TagImpl(
        "89",
        TagValueTypeEnum.BINARY,
        "Authorisation Code",
        "Value generated by the authorisation authority for an approved transaction"
    )
    val AUTHORISATION_RESPONSE_CODE: ITag = TagImpl(
        "8a",
        TagValueTypeEnum.TEXT,
        "Authorisation Response Code",
        "Code that defines the disposition of a message"
    )
    val CDOL1: ITag = TagImpl(
        "8c",
        TagValueTypeEnum.DOL,
        "Card Risk Management Data Object List 1 (CDOL1)",
        "List of data objects (tag and length) to be passed to the ICC in the first GENERATE AC command"
    )
    val CDOL2: ITag = TagImpl(
        "8d",
        TagValueTypeEnum.DOL,
        "Card Risk Management Data Object List 2 (CDOL2)",
        "List of data objects (tag and length) to be passed to the ICC in the second GENERATE AC command"
    )
    val CVM_LIST: ITag = TagImpl(
        "8e",
        TagValueTypeEnum.BINARY,
        "Cardholder Verification Method (CVM) List",
        "Identifies a method of verification of the cardholder supported by the application"
    )
    val CA_PUBLIC_KEY_INDEX_CARD: ITag = TagImpl(
        "8f",
        TagValueTypeEnum.BINARY,
        "Certification Authority Public Key Index - card",
        "Identifies the certification authorityâ€™s public key in conjunction with the RID"
    )
    val ISSUER_PUBLIC_KEY_CERT: ITag = TagImpl(
        "90",
        TagValueTypeEnum.BINARY,
        "Issuer Public Key Certificate",
        "Issuer public key certified by a certification authority"
    )
    val ISSUER_AUTHENTICATION_DATA: ITag = TagImpl(
        "91",
        TagValueTypeEnum.BINARY,
        "Issuer Authentication Data",
        "Data sent to the ICC for online issuer authentication"
    )
    val ISSUER_PUBLIC_KEY_REMAINDER: ITag = TagImpl(
        "92",
        TagValueTypeEnum.BINARY,
        "Issuer Public Key Remainder",
        "Remaining digits of the Issuer Public Key Modulus"
    )
    val SIGNED_STATIC_APP_DATA: ITag = TagImpl(
        "93",
        TagValueTypeEnum.BINARY,
        "Signed Static Application Data",
        "Digital signature on critical application parameters for SDA"
    )

    @JvmField
    val APPLICATION_FILE_LOCATOR: ITag = TagImpl(
        "94",
        TagValueTypeEnum.BINARY,
        "Application File Locator (AFL)",
        "Indicates the location (SFI, range of records) of the AEFs related to a given application"
    )
    val TERMINAL_VERIFICATION_RESULTS: ITag = TagImpl(
        "95",
        TagValueTypeEnum.BINARY,
        "Terminal Verification Results (TVR)",
        "Status of the different functions as seen from the terminal"
    )
    val TDOL: ITag = TagImpl(
        "97",
        TagValueTypeEnum.BINARY,
        "Transaction Certificate Data Object List (TDOL)",
        "List of data objects (tag and length) to be used by the terminal in generating the TC Hash Value"
    )
    val TC_HASH_VALUE: ITag = TagImpl(
        "98",
        TagValueTypeEnum.BINARY,
        "Transaction Certificate (TC) Hash Value",
        "Result of a hash function specified in Book 2, Annex B3.1"
    )
    val TRANSACTION_PIN_DATA: ITag = TagImpl(
        "99",
        TagValueTypeEnum.BINARY,
        "Transaction Personal Identification Number (PIN) Data",
        "Data entered by the cardholder for the purpose of the PIN verification"
    )

    @JvmField
    val TRANSACTION_DATE: ITag = TagImpl(
        "9a",
        TagValueTypeEnum.NUMERIC,
        "Transaction Date",
        "Local date that the transaction was authorised"
    )
    val TRANSACTION_STATUS_INFORMATION: ITag = TagImpl(
        "9b",
        TagValueTypeEnum.BINARY,
        "Transaction Status Information",
        "Indicates the functions performed in a transaction"
    )

    @JvmField
    val TRANSACTION_TYPE: ITag = TagImpl(
        "9c",
        TagValueTypeEnum.NUMERIC,
        "Transaction Type",
        "Indicates the type of financial transaction, represented by the first two digits of ISO 8583:1987 Processing Code"
    )
    val DDF_NAME: ITag = TagImpl(
        "9d",
        TagValueTypeEnum.BINARY,
        "Directory Definition File (DDF) Name",
        "Identifies the name of a DF associated with a directory"
    )

    // Two byte tags
    @JvmField
    val CARDHOLDER_NAME: ITag = TagImpl(
        "5f20",
        TagValueTypeEnum.TEXT,
        "Cardholder Name",
        "Indicates cardholder name according to ISO 7813"
    )
    val APP_EXPIRATION_DATE: ITag = TagImpl(
        "5f24",
        TagValueTypeEnum.NUMERIC,
        "Application Expiration Date",
        "Date after which application expires"
    )
    val APP_EFFECTIVE_DATE: ITag = TagImpl(
        "5f25",
        TagValueTypeEnum.NUMERIC,
        "Application Effective Date",
        "Date from which the application may be used"
    )
    val ISSUER_COUNTRY_CODE: ITag = TagImpl(
        "5f28",
        TagValueTypeEnum.NUMERIC,
        "Issuer Country Code",
        "Indicates the country of the issuer according to ISO 3166"
    )

    @JvmField
    val TRANSACTION_CURRENCY_CODE: ITag = TagImpl(
        "5f2a",
        TagValueTypeEnum.TEXT,
        "Transaction Currency Code",
        "Indicates the currency code of the transaction according to ISO 4217"
    )
    val LANGUAGE_PREFERENCE: ITag = TagImpl(
        "5f2d",
        TagValueTypeEnum.TEXT,
        "Language Preference",
        "1â€“4 languages stored in order of preference, each represented by 2 alphabetical characters according to ISO 639"
    )
    val SERVICE_CODE: ITag = TagImpl(
        "5f30",
        TagValueTypeEnum.NUMERIC,
        "Service Code",
        "Service code as defined in ISO/IEC 7813 for track 1 and track 2"
    )
    val PAN_SEQUENCE_NUMBER: ITag = TagImpl(
        "5f34",
        TagValueTypeEnum.NUMERIC,
        "Application Primary Account Number (PAN) Sequence Number",
        "Identifies and differentiates cards with the same PAN"
    )
    val TRANSACTION_CURRENCY_EXP: ITag = TagImpl(
        "5f36",
        TagValueTypeEnum.NUMERIC,
        "Transaction Currency Exponent",
        "Indicates the implied position of the decimal point from the right of the transaction amount represented according to ISO 4217"
    )
    val IBAN: ITag = TagImpl(
        "5f53",
        TagValueTypeEnum.BINARY,
        "International Bank Account Number (IBAN)",
        "Uniquely identifies the account of a customer at a financial institution as defined in ISO 13616"
    )
    val BANK_IDENTIFIER_CODE: ITag = TagImpl(
        "5f54",
        TagValueTypeEnum.MIXED,
        "Bank Identifier Code (BIC)",
        "Uniquely identifies a bank as defined in ISO 9362"
    )
    val ISSUER_COUNTRY_CODE_ALPHA2: ITag = TagImpl(
        "5f55",
        TagValueTypeEnum.TEXT,
        "Issuer Country Code (alpha2 format)",
        "Indicates the country of the issuer as defined in ISO 3166 (using a 2 character alphabetic code)"
    )
    val ISSUER_COUNTRY_CODE_ALPHA3: ITag = TagImpl(
        "5f56",
        TagValueTypeEnum.TEXT,
        "Issuer Country Code (alpha3 format)",
        "Indicates the country of the issuer as defined in ISO 3166 (using a 3 character alphabetic code)"
    )
    val ACQUIRER_IDENTIFIER: ITag = TagImpl(
        "9f01",
        TagValueTypeEnum.NUMERIC,
        "Acquirer Identifier",
        "Uniquely identifies the acquirer within each payment system"
    )

    @JvmField
    val AMOUNT_AUTHORISED_NUMERIC: ITag = TagImpl(
        "9f02",
        TagValueTypeEnum.NUMERIC,
        "Amount, Authorised (Numeric)",
        "Authorised amount of the transaction (excluding adjustments)"
    )
    val AMOUNT_OTHER_NUMERIC: ITag = TagImpl(
        "9f03",
        TagValueTypeEnum.NUMERIC,
        "Amount, Other (Numeric)",
        "Secondary amount associated with the transaction representing a cashback amount"
    )
    val AMOUNT_OTHER_BINARY: ITag = TagImpl(
        "9f04",
        TagValueTypeEnum.NUMERIC,
        "Amount, Other (Binary)",
        "Secondary amount associated with the transaction representing a cashback amount"
    )
    val APP_DISCRETIONARY_DATA: ITag = TagImpl(
        "9f05",
        TagValueTypeEnum.BINARY,
        "Application Discretionary Data",
        "Issuer or payment system specified data relating to the application"
    )
    val AID_TERMINAL: ITag = TagImpl(
        "9f06",
        TagValueTypeEnum.BINARY,
        "Application Identifier (AID) - terminal",
        "Identifies the application as described in ISO/IEC 7816-5"
    )
    val APP_USAGE_CONTROL: ITag = TagImpl(
        "9f07",
        TagValueTypeEnum.BINARY,
        "Application Usage Control",
        "Indicates issuerâ€™s specified restrictions on the geographic usage and services allowed for the application"
    )
    val APP_VERSION_NUMBER_CARD: ITag = TagImpl(
        "9f08",
        TagValueTypeEnum.BINARY,
        "Application Version Number - card",
        "Version number assigned by the payment system for the application"
    )
    val APP_VERSION_NUMBER_TERMINAL: ITag = TagImpl(
        "9f09",
        TagValueTypeEnum.BINARY,
        "Application Version Number - terminal",
        "Version number assigned by the payment system for the application"
    )
    val CARDHOLDER_NAME_EXTENDED: ITag = TagImpl(
        "9f0b",
        TagValueTypeEnum.TEXT,
        "Cardholder Name Extended",
        "Indicates the whole cardholder name when greater than 26 characters using the same coding convention as in ISO 7813"
    )
    val ISSUER_ACTION_CODE_DEFAULT: ITag = TagImpl(
        "9f0d",
        TagValueTypeEnum.BINARY,
        "Issuer Action Code - Default",
        "Specifies the issuerâ€™s conditions that cause a transaction to be rejected if it might have been approved online, but the terminal is unable to process the transaction online"
    )
    val ISSUER_ACTION_CODE_DENIAL: ITag = TagImpl(
        "9f0e",
        TagValueTypeEnum.BINARY,
        "Issuer Action Code - Denial",
        "Specifies the issuerâ€™s conditions that cause the denial of a transaction without attempt to go online"
    )
    val ISSUER_ACTION_CODE_ONLINE: ITag = TagImpl(
        "9f0f",
        TagValueTypeEnum.BINARY,
        "Issuer Action Code - Online",
        "Specifies the issuerâ€™s conditions that cause a transaction to be transmitted online"
    )
    val ISSUER_APPLICATION_DATA: ITag = TagImpl(
        "9f10",
        TagValueTypeEnum.BINARY,
        "Issuer Application Data",
        "Contains proprietary application data for transmission to the issuer in an online transaction"
    )
    val ISSUER_CODE_TABLE_INDEX: ITag = TagImpl(
        "9f11",
        TagValueTypeEnum.NUMERIC,
        "Issuer Code Table Index",
        "Indicates the code table according to ISO/IEC 8859 for displaying the Application Preferred Name"
    )
    val APP_PREFERRED_NAME: ITag = TagImpl(
        "9f12",
        TagValueTypeEnum.TEXT,
        "Application Preferred Name",
        "Preferred mnemonic associated with the AID"
    )
    val LAST_ONLINE_ATC_REGISTER: ITag = TagImpl(
        "9f13",
        TagValueTypeEnum.BINARY,
        "Last Online Application Transaction Counter (ATC) Register",
        "ATC value of the last transaction that went online"
    )
    val LOWER_CONSEC_OFFLINE_LIMIT: ITag = TagImpl(
        "9f14",
        TagValueTypeEnum.BINARY,
        "Lower Consecutive Offline Limit",
        "Issuer-specified preference for the maximum number of consecutive offline transactions for this ICC application allowed in a terminal with online capability"
    )
    val MERCHANT_CATEGORY_CODE: ITag = TagImpl(
        "9f15",
        TagValueTypeEnum.NUMERIC,
        "Merchant Category Code",
        "Classifies the type of business being done by the merchant, represented according to ISO 8583:1993 for Card Acceptor Business Code"
    )
    val MERCHANT_IDENTIFIER: ITag = TagImpl(
        "9f16",
        TagValueTypeEnum.TEXT,
        "Merchant Identifier",
        "When concatenated with the Acquirer Identifier, uniquely identifies a given merchant"
    )

    @JvmField
    val PIN_TRY_COUNTER: ITag = TagImpl(
        "9f17",
        TagValueTypeEnum.BINARY,
        "Personal Identification Number (PIN) Try Counter",
        "Number of PIN tries remaining"
    )
    val ISSUER_SCRIPT_IDENTIFIER: ITag = TagImpl(
        "9f18",
        TagValueTypeEnum.BINARY,
        "Issuer Script Identifier",
        "Identification of the Issuer Script"
    )

    @JvmField
    val TERMINAL_COUNTRY_CODE: ITag = TagImpl(
        "9f1a",
        TagValueTypeEnum.TEXT,
        "Terminal Country Code",
        "Indicates the country of the terminal, represented according to ISO 3166"
    )
    val TERMINAL_FLOOR_LIMIT: ITag = TagImpl(
        "9f1b",
        TagValueTypeEnum.BINARY,
        "Terminal Floor Limit",
        "Indicates the floor limit in the terminal in conjunction with the AID"
    )
    val TERMINAL_IDENTIFICATION: ITag = TagImpl(
        "9f1c",
        TagValueTypeEnum.TEXT,
        "Terminal Identification",
        "Designates the unique location of a terminal at a merchant"
    )
    val TERMINAL_RISK_MANAGEMENT_DATA: ITag = TagImpl(
        "9f1d",
        TagValueTypeEnum.BINARY,
        "Terminal Risk Management Data",
        "Application-specific value used by the card for risk management purposes"
    )
    val INTERFACE_DEVICE_SERIAL_NUMBER: ITag = TagImpl(
        "9f1e",
        TagValueTypeEnum.TEXT,
        "Interface Device (IFD) Serial Number",
        "Unique and permanent serial number assigned to the IFD by the manufacturer"
    )
    val TRACK1_DISCRETIONARY_DATA: ITag = TagImpl(
        "9f1f",
        TagValueTypeEnum.TEXT,
        "[Magnetic Stripe] Track 1 Discretionary Data",
        "Discretionary part of track 1 according to ISO/IEC 7813"
    )
    val TRACK2_DISCRETIONARY_DATA: ITag = TagImpl(
        "9f20",
        TagValueTypeEnum.TEXT,
        "[Magnetic Stripe] Track 2 Discretionary Data",
        "Discretionary part of track 2 according to ISO/IEC 7813"
    )
    val TRANSACTION_TIME: ITag = TagImpl(
        "9f21",
        TagValueTypeEnum.NUMERIC,
        "Transaction Time (HHMMSS)",
        "Local time that the transaction was authorised"
    )
    val CA_PUBLIC_KEY_INDEX_TERMINAL: ITag = TagImpl(
        "9f22",
        TagValueTypeEnum.BINARY,
        "Certification Authority Public Key Index - Terminal",
        "Identifies the certification authorityâ€™s public key in conjunction with the RID"
    )
    val UPPER_CONSEC_OFFLINE_LIMIT: ITag = TagImpl(
        "9f23",
        TagValueTypeEnum.BINARY,
        "Upper Consecutive Offline Limit",
        "Issuer-specified preference for the maximum number of consecutive offline transactions for this ICC application allowed in a terminal without online capability"
    )
    val APP_CRYPTOGRAM: ITag = TagImpl(
        "9f26",
        TagValueTypeEnum.BINARY,
        "Application Cryptogram",
        "Cryptogram returned by the ICC in response of the GENERATE AC command"
    )
    val CRYPTOGRAM_INFORMATION_DATA: ITag = TagImpl(
        "9f27",
        TagValueTypeEnum.BINARY,
        "Cryptogram Information Data",
        "Indicates the type of cryptogram and the actions to be performed by the terminal"
    )
    val ICC_PIN_ENCIPHERMENT_PUBLIC_KEY_CERT: ITag = TagImpl(
        "9f2d",
        TagValueTypeEnum.BINARY,
        "ICC PIN Encipherment Public Key Certificate",
        "ICC PIN Encipherment Public Key certified by the issuer"
    )
    val ICC_PIN_ENCIPHERMENT_PUBLIC_KEY_EXP: ITag = TagImpl(
        "9f2e",
        TagValueTypeEnum.BINARY,
        "ICC PIN Encipherment Public Key Exponent",
        "ICC PIN Encipherment Public Key Exponent used for PIN encipherment"
    )
    val ICC_PIN_ENCIPHERMENT_PUBLIC_KEY_REM: ITag = TagImpl(
        "9f2f",
        TagValueTypeEnum.BINARY,
        "ICC PIN Encipherment Public Key Remainder",
        "Remaining digits of the ICC PIN Encipherment Public Key Modulus"
    )
    val ISSUER_PUBLIC_KEY_EXP: ITag = TagImpl(
        "9f32",
        TagValueTypeEnum.BINARY,
        "Issuer Public Key Exponent",
        "Issuer public key exponent used for the verification of the Signed Static Application Data and the ICC Public Key Certificate"
    )

    @JvmField
    val TERMINAL_CAPABILITIES: ITag = TagImpl(
        "9f33",
        TagValueTypeEnum.BINARY,
        "Terminal Capabilities",
        "Indicates the card data input, CVM, and security capabilities of the terminal"
    )
    val CVM_RESULTS: ITag = TagImpl(
        "9f34",
        TagValueTypeEnum.BINARY,
        "Cardholder Verification (CVM) Results",
        "Indicates the results of the last CVM performed"
    )

    @JvmField
    val TERMINAL_TYPE: ITag = TagImpl(
        "9f35",
        TagValueTypeEnum.NUMERIC,
        "Terminal Type",
        "Indicates the environment of the terminal, its communications capability, and its operational control"
    )
    val APP_TRANSACTION_COUNTER: ITag = TagImpl(
        "9f36",
        TagValueTypeEnum.BINARY,
        "Application Transaction Counter (ATC)",
        "Counter maintained by the application in the ICC (incrementing the ATC is managed by the ICC)"
    )

    @JvmField
    val UNPREDICTABLE_NUMBER: ITag = TagImpl(
        "9f37",
        TagValueTypeEnum.BINARY,
        "Unpredictable Number",
        "Value to provide variability and uniqueness to the generation of a cryptogram"
    )

    @JvmField
    val PDOL: ITag = TagImpl(
        "9f38",
        TagValueTypeEnum.DOL,
        "Processing Options Data Object List (PDOL)",
        "Contains a list of terminal resident data objects (tags and lengths) needed by the ICC in processing the GET PROCESSING OPTIONS command"
    )
    val POINT_OF_SERVICE_ENTRY_MODE: ITag = TagImpl(
        "9f39",
        TagValueTypeEnum.NUMERIC,
        "Point-of-Service (POS) Entry Mode",
        "Indicates the method by which the PAN was entered, according to the first two digits of the ISO 8583:1987 POS Entry Mode"
    )
    val AMOUNT_REFERENCE_CURRENCY: ITag = TagImpl(
        "9f3a",
        TagValueTypeEnum.BINARY,
        "Amount, Reference Currency",
        "Authorised amount expressed in the reference currency"
    )
    val APP_REFERENCE_CURRENCY: ITag = TagImpl(
        "9f3b",
        TagValueTypeEnum.NUMERIC,
        "Application Reference Currency",
        "1â€“4 currency codes used between the terminal and the ICC when the Transaction Currency Code is different from the Application Currency Code; each code is 3 digits according to ISO 4217"
    )
    val TRANSACTION_REFERENCE_CURRENCY_CODE: ITag = TagImpl(
        "9f3c",
        TagValueTypeEnum.NUMERIC,
        "Transaction Reference Currency Code",
        "Code defining the common currency used by the terminal in case the Transaction Currency Code is different from the Application Currency Code"
    )
    val TRANSACTION_REFERENCE_CURRENCY_EXP: ITag = TagImpl(
        "9f3d",
        TagValueTypeEnum.NUMERIC,
        "Transaction Reference Currency Exponent",
        "Indicates the implied position of the decimal point from the right of the transaction amount, with the Transaction Reference Currency Code represented according to ISO 4217"
    )

    @JvmField
    val ADDITIONAL_TERMINAL_CAPABILITIES: ITag = TagImpl(
        "9f40",
        TagValueTypeEnum.BINARY,
        "Additional Terminal Capabilities",
        "Indicates the data input and output capabilities of the terminal"
    )
    val TRANSACTION_SEQUENCE_COUNTER: ITag = TagImpl(
        "9f41",
        TagValueTypeEnum.NUMERIC,
        "Transaction Sequence Counter",
        "Counter maintained by the terminal that is incremented by one for each transaction"
    )
    val APPLICATION_CURRENCY_CODE: ITag = TagImpl(
        "9f42",
        TagValueTypeEnum.NUMERIC,
        "Application Currency Code",
        "Indicates the currency in which the account is managed according to ISO 4217"
    )
    val APP_REFERENCE_CURRECY_EXPONENT: ITag = TagImpl(
        "9f43",
        TagValueTypeEnum.NUMERIC,
        "Application Reference Currency Exponent",
        "Indicates the implied position of the decimal point from the right of the amount, for each of the 1â€“4 reference currencies represented according to ISO 4217"
    )
    val APP_CURRENCY_EXPONENT: ITag = TagImpl(
        "9f44",
        TagValueTypeEnum.NUMERIC,
        "Application Currency Exponent",
        "Indicates the implied position of the decimal point from the right of the amount represented according to ISO 4217"
    )
    val DATA_AUTHENTICATION_CODE: ITag = TagImpl(
        "9f45",
        TagValueTypeEnum.BINARY,
        "Data Authentication Code",
        "An issuer assigned value that is retained by the terminal during the verification process of the Signed Static Application Data"
    )
    val ICC_PUBLIC_KEY_CERT: ITag = TagImpl(
        "9f46",
        TagValueTypeEnum.BINARY,
        "ICC Public Key Certificate",
        "ICC Public Key certified by the issuer"
    )
    val ICC_PUBLIC_KEY_EXP: ITag = TagImpl(
        "9f47",
        TagValueTypeEnum.BINARY,
        "ICC Public Key Exponent",
        "ICC Public Key Exponent used for the verification of the Signed Dynamic Application Data"
    )
    val ICC_PUBLIC_KEY_REMAINDER: ITag = TagImpl(
        "9f48",
        TagValueTypeEnum.BINARY,
        "ICC Public Key Remainder",
        "Remaining digits of the ICC Public Key Modulus"
    )
    val DDOL: ITag = TagImpl(
        "9f49",
        TagValueTypeEnum.DOL,
        "Dynamic Data Authentication Data Object List (DDOL)",
        "List of data objects (tag and length) to be passed to the ICC in the INTERNAL AUTHENTICATE command"
    )
    val SDA_TAG_LIST: ITag = TagImpl(
        "9f4a",
        TagValueTypeEnum.BINARY,
        "Static Data Authentication Tag List",
        "List of tags of primitive data objects defined in this specification whose value fields are to be included in the Signed Static or Dynamic Application Data"
    )
    val SIGNED_DYNAMIC_APPLICATION_DATA: ITag = TagImpl(
        "9f4b",
        TagValueTypeEnum.BINARY,
        "Signed Dynamic Application Data",
        "Digital signature on critical application parameters for DDA or CDA"
    )
    val ICC_DYNAMIC_NUMBER: ITag = TagImpl(
        "9f4c",
        TagValueTypeEnum.BINARY,
        "ICC Dynamic Number",
        "Time-variant number generated by the ICC, to be captured by the terminal"
    )

    @JvmField
    val LOG_ENTRY: ITag = TagImpl(
        "9f4d",
        TagValueTypeEnum.BINARY,
        "Log Entry",
        "Provides the SFI of the Transaction Log file and its number of records"
    )
    val MERCHANT_NAME_AND_LOCATION: ITag = TagImpl(
        "9f4e",
        TagValueTypeEnum.TEXT,
        "Merchant Name and Location",
        "Indicates the name and location of the merchant"
    )

    @JvmField
    val LOG_FORMAT: ITag = TagImpl(
        "9f4f",
        TagValueTypeEnum.DOL,
        "Log Format",
        "List (in tag and length format) of data objects representing the logged data elements that are passed to the terminal when a transaction log record is read"
    )
    val FCI_ISSUER_DISCRETIONARY_DATA: ITag = TagImpl(
        "bf0c",
        TagValueTypeEnum.BINARY,
        "File Control Information (FCI) Issuer Discretionary Data",
        "Issuer discretionary part of the FCI (e.g. O/S Manufacturer proprietary data)"
    )

    @JvmField
    val VISA_LOG_ENTRY: ITag = TagImpl("df60", TagValueTypeEnum.BINARY, "VISA Log Entry", "")

    // '9F50' to '9F7F' are reserved for the payment systems (proprietary)
    // The following tags are specified in EMV Contactless (Book A)
    // The Track 1 Data may be present in the file read using the READ
    // RECORD command during a mag-stripe mode transaction. It is made up of
    // the following sub-fields:
    // +------------------------+--------------+--------------------+
    // | Data Field | Length | Format |
    // +------------------------+--------------+--------------------+
    // | Format Code | 1 | '42' |
    // | Primary Account Number | var up to 19 | digits |
    // | Field Separator | 1 | '5E' |
    // | Name | 2-26 | (see ISO/IEC 7813) |
    // | Field Separator | 1 | '5E' |
    // | Expiry Date | 4 | YYMM |
    // | Service Code | 3 | digits |
    // | Discretionary Data | var. | ans |
    // +------------------------+--------------+--------------------+
    // BER-TLV[56, 29 (raw 29), 42 xx xx xx xx xx xx xx xx xx xx xx xx xx xx xx xx 5e 20 2f 5e xx xx xx xx 32 30 31 30 31 30 30 30
    // 30 30 30 30 30 30 30 30]
    // BER-TLV[56, 34 (raw 34), 42 XX XX XX XX XX XX XX XX XX XX XX XX XX XX XX XX 5e 20 2f 5e YY YY MM MM 32 30 31 30 30 30 30 30
    // 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30
    val TRACK1_DATA: ITag = TagImpl(
        "56",
        TagValueTypeEnum.BINARY,
        "Track 1 Data",
        "Track 1 Data contains the data objects of the track 1 according to [ISO/IEC 7813] Structure B, excluding start sentinel, end sentinel and LRC."
    )

    @JvmField
    val TERMINAL_TRANSACTION_QUALIFIERS: ITag = TagImpl(
        "9f66",
        TagValueTypeEnum.BINARY,
        "Terminal Transaction Qualifiers",
        "Provided by the reader in the GPO command and used by the card to determine processing choices based on reader functionality"
    )

    // The Track 2 Data is present in the file read using the READ RECORD command
    // during a mag-stripe mode transaction. It is made up of the following
    // sub-fields (same as tag 57):
    //
    // +------------------------+-----------------------+-----------+
    // | Data Field | Length | Format |
    // +------------------------+-----------------------+-----------+
    // | Primary Account Number | var. up to 19 nibbles | n |
    // | Field Separator | 1 nibble | b ('D') |
    // | Expiry Date | 2 | n (YYMM) |
    // | Service Code | 3 nibbles | n |
    // | Discretionary Data | var. | n |
    // | Padding if needed | 1 nibble | b ('F') |
    // +------------------------+-----------------------+-----------+
    // 9f6b 13 BB BB BB BB BB BB BB BB dY YM M2 01 00 00 00 00 00 00 0f
    @JvmField
    val TRACK2_DATA: ITag = TagImpl(
        "9f6b",
        TagValueTypeEnum.BINARY,
        "Track 2 Data",
        "Track 2 Data contains the data objects of the track 2 according to [ISO/IEC 7813] Structure B, excluding start sentinel, end sentinel and LRC."
    )
    val VLP_ISSUER_AUTHORISATION_CODE: ITag = TagImpl(
        "9f6e",
        TagValueTypeEnum.BINARY,
        "Visa Low-Value Payment (VLP) Issuer Authorisation Code",
        ""
    )

    // These are specified in EMV Contactless (Book B)
    val EXTENDED_SELECTION: ITag = TagImpl(
        "9f29",
        TagValueTypeEnum.BINARY,
        "Indicates the card's preference for the kernel on which the contactless application can be processed",
        ""
    )

    @JvmField
    val KERNEL_IDENTIFIER: ITag = TagImpl(
        "9f2a",
        TagValueTypeEnum.BINARY,
        "The value to be appended to the ADF Name in the data field of the SELECT command, if the Extended Selection Support flag is present and set to 1",
        ""
    )

    // MasterCard Tags
    val MASTERCARD_UPPER_OFFLINE_AMOUNT: ITag = TagImpl(
        "9f52",
        TagValueTypeEnum.BINARY,
        "Upper Cumulative Domestic Offline Transaction Amount",
        "Issuer specified data element indicating the required maximum cumulative offline amount allowed for the application before the transaction goes online."
    )

    // //EMV-CAP tags (see also VISA Tags)
    // //9f55 01 c0
    // //9f55 01 00
    // public static final Tag TAG_9f55 = new TagImpl("9f55", TagValueTypeEnum.BINARY, "?", "");
    // EMV Cap
    // 9f56 0c 0f00007fffffe00000000000
    // 9f56 1d 00007fffffe00000000000000000000000000000000000000000000000
    // 9f56 0b 00 00 7f ff ff 00 00 00 00 00 00
    val TAG_9f56: ITag = TagImpl("9f56", TagValueTypeEnum.BINARY, "?", "")

    // Example: BER-TLV[9f6c, 02 (raw 02), 0001]
    val MAG_STRIPE_APP_VERSION_NUMBER_CARD: ITag = TagImpl(
        "9f6c",
        TagValueTypeEnum.BINARY,
        "Mag Stripe Application Version Number (Card)",
        "Must be personalized with the value 0x0001"
    )

    // Transaction log data
    // df3e 01 01
    val TAG_df3e: ITag = TagImpl("df3e", TagValueTypeEnum.BINARY, "?", "")

    // TODO are these issuer specific?
    // Card from portugal (TODO find issuer code)
    // a000000004 Unhandled tags:
    //
    // df48 02 0620
    // df40 01 00
    // df27 08 0103000000000000
    // df28 10 ffffffffffffffffffffffffffffffff
    // df47 01 01
    // df49 0d 48000001011900000000000000
    // df44 28 00000000000000000000202020202020202020202020202020202020202020202020202020202020
    // df45 22 00000000202020202020202020202020202020202020202020202020202020202020
    // df46 03 000000
    //
    //
    // 501649ff20 Unhandled tags:
    //
    // df48 02 0620
    // df40 01 00
    // df27 08 0100000000000000
    // df28 10 ffffffffffffffffffffffffffffffff
    // df47 01 01
    // df49 0d 00000001010000000000000000
    // TODO refactor to Kernel 2 Tags
    // These are specified in EMV Contactless (Book C-2) "MasterCard"
    val OFFLINE_ACCUMULATOR_BALANCE: ITag = TagImpl(
        "9f50",
        TagValueTypeEnum.BINARY,
        "Offline Accumulator Balance",
        "Represents the amount of offline spending available in the Card."
    )

    // 9f51 03 9f 37 04
    val DRDOL: ITag = TagImpl(
        "9f51",
        TagValueTypeEnum.BINARY,
        "DRDOL",
        "A data object in the Card that provides the Kernel with a list of data objects that must be passed to the Card in the data field of the RECOVER AC command"
    )
    val TRANSACTION_CATEGORY_CODE: ITag =
        TagImpl("9f53", TagValueTypeEnum.BINARY, "Transaction Category Code", "")
    val DS_ODS_CARD: ITag = TagImpl("9f54", TagValueTypeEnum.BINARY, "DS ODS Card", "")
    val MOBILE_SUPPORT_INDICATOR: ITag =
        TagImpl("9f55", TagValueTypeEnum.BINARY, "Mobile Support Indicator", "")
    val DSDOL: ITag = TagImpl("9f5b", TagValueTypeEnum.BINARY, "DSDOL", "")

    @JvmField
    val DS_REQUESTED_OPERATOR_ID: ITag =
        TagImpl("9f5c", TagValueTypeEnum.BINARY, "DS Requested Operator ID", "")

    // 9f5d 01 01
    val APPLICATION_CAPABILITIES_INFORMATION: ITag = TagImpl(
        "9f5d",
        TagValueTypeEnum.BINARY,
        "Application Capabilities Information",
        "Lists a number of card features beyond regular payment"
    )
    val DS_ID: ITag = TagImpl(
        "9f5e",
        TagValueTypeEnum.BINARY,
        "Data Storage Identifier",
        "Constructed as follows: Application PAN (without any 'F' padding) || Application PAN Sequence Number (+ zero padding)"
    )
    val DS_SLOT_AVAILABILITY: ITag =
        TagImpl("9f5f", TagValueTypeEnum.BINARY, "DS Slot Availability", "")
    val CVC3_TRACK1: ITag = TagImpl(
        "9f60",
        TagValueTypeEnum.BINARY,
        "CVC3 (Track1)",
        "The CVC3 (Track1) is a 2-byte cryptogram returned by the Card in the response to the COMPUTE CRYPTOGRAPHIC CHECKSUM command."
    )
    val CVC3_TRACK2: ITag = TagImpl(
        "9f61",
        TagValueTypeEnum.BINARY,
        "CVC3 (Track2)",
        "The CVC3 (Track2) is a 2-byte cryptogram returned by the Card in the response to the COMPUTE CRYPTOGRAPHIC CHECKSUM command."
    )

    // 9f62 06 00 00 00 00 00 0e
    // 9f62 06 00 00 00 03 80 00
    val PCVC3_TRACK1: ITag = TagImpl(
        "9f62",
        TagValueTypeEnum.BINARY,
        "Track 1 bit map for CVC3",
        "PCVC3(Track1) indicates to the Kernel the positions in the discretionary data field of the Track 1 Data where the CVC3 (Track1) digits must be copied"
    )

    // 9f63 06 00 00 00 00 07 f0
    // 9f63 06 00 00 00 00 07 8e
    val PUNTAC_TRACK1: ITag = TagImpl(
        "9f63",
        TagValueTypeEnum.BINARY,
        "Track 1 bit map for UN and ATC",
        "PUNATC(Track1) indicates to the Kernel the positions in the discretionary data field of Track 1 Data where the Unpredictable Number (Numeric) digits and Application Transaction Counter digits have to be copied."
    )

    // 9f64 01 03
    // 9f64 01 04
    val NATC_TRACK1: ITag = TagImpl(
        "9f64",
        TagValueTypeEnum.BINARY,
        "Track 1 number of ATC digits",
        "The value of NATC(Track1) represents the number of digits of the Application Transaction Counter to be included in the discretionary data field of Track 1 Data"
    )

    // 9f65 02 000e
    // 9f65 02 0070
    val PCVC_TRACK2: ITag = TagImpl(
        "9f65",
        TagValueTypeEnum.BINARY,
        "Track 2 bit map for CVC3",
        "PCVC3(Track2) indicates to the Kernel the positions in the discretionary data field of the Track 2 Data where the CVC3 (Track2) digits must be copied"
    )

    // 9f66 02 07f0
    // 9f66 02 1e0e
    val NATC_TRACK2: ITag = TagImpl(
        "9f67",
        TagValueTypeEnum.BINARY,
        "Track 2 number of ATC digits",
        "The value of NATC(Track2) represents the number of digits of the Application Transaction Counter to be included in the discretionary data field of Track 2 Data"
    )
    val UDOL: ITag = TagImpl("9f69", TagValueTypeEnum.BINARY, "UDOL", "")
    val UNPREDICTABLE_NUMBER_NUMERIC: ITag =
        TagImpl("9f6a", TagValueTypeEnum.BINARY, "Unpredictable Number (Numeric)", "")
    val MAG_STRIPE_APP_VERSION_NUMBER_READER: ITag = TagImpl(
        "9f6d",
        TagValueTypeEnum.BINARY,
        "Mag-stripe Application Version Number (Reader)",
        ""
    )
    val DS_SLOT_MANAGEMENT_CONTROL: ITag =
        TagImpl("9f6f", TagValueTypeEnum.BINARY, "DS Slot Management Control", "")
    val PROTECTED_DATA_ENVELOPE_1: ITag =
        TagImpl("9f70", TagValueTypeEnum.BINARY, "Protected Data Envelope 1", "")
    val PROTECTED_DATA_ENVELOPE_2: ITag =
        TagImpl("9f71", TagValueTypeEnum.BINARY, "Protected Data Envelope 2", "")
    val PROTECTED_DATA_ENVELOPE_3: ITag =
        TagImpl("9f72", TagValueTypeEnum.BINARY, "Protected Data Envelope 3", "")
    val PROTECTED_DATA_ENVELOPE_4: ITag =
        TagImpl("9f73", TagValueTypeEnum.BINARY, "Protected Data Envelope 4", "")
    val PROTECTED_DATA_ENVELOPE_5: ITag =
        TagImpl("9f74", TagValueTypeEnum.BINARY, "Protected Data Envelope 5", "")
    val UNPROTECTED_DATA_ENVELOPE_1: ITag =
        TagImpl("9f75", TagValueTypeEnum.BINARY, "Unprotected Data Envelope 1", "")
    val UNPROTECTED_DATA_ENVELOPE_2: ITag =
        TagImpl("9f76", TagValueTypeEnum.BINARY, "Unprotected Data Envelope 2", "")
    val UNPROTECTED_DATA_ENVELOPE_3: ITag =
        TagImpl("9f77", TagValueTypeEnum.BINARY, "Unprotected Data Envelope 3", "")
    val UNPROTECTED_DATA_ENVELOPE_4: ITag =
        TagImpl("9f78", TagValueTypeEnum.BINARY, "Unprotected Data Envelope 4", "")
    val UNPROTECTED_DATA_ENVELOPE_5: ITag =
        TagImpl("9f79", TagValueTypeEnum.BINARY, "Unprotected Data Envelope 5", "")
    val MERCHANT_CUSTOM_DATA: ITag =
        TagImpl("9f7c", TagValueTypeEnum.BINARY, "Merchant Custom Data", "")
    val DS_SUMMARY_1: ITag = TagImpl("9f7d", TagValueTypeEnum.BINARY, "DS Summary 1", "")
    val DS_UNPREDICTABLE_NUMBER: ITag =
        TagImpl("9f7f", TagValueTypeEnum.BINARY, "DS Unpredictable Number", "")
    val POS_CARDHOLDER_INTERACTION_INFORMATION: ITag =
        TagImpl("df4b", TagValueTypeEnum.BINARY, "POS Cardholder Interaction Information", "")
    val DS_DIGEST_H: ITag = TagImpl("df61", TagValueTypeEnum.BINARY, "DS Digest H", "")
    val DS_ODS_INFO: ITag = TagImpl("df62", TagValueTypeEnum.BINARY, "DS ODS Info", "")
    val DS_ODS_TERM: ITag = TagImpl("df63", TagValueTypeEnum.BINARY, "DS ODS Term", "")
    val BALANCE_READ_BEFORE_GEN_AC: ITag =
        TagImpl("df8104", TagValueTypeEnum.BINARY, "Balance Read Before Gen AC", "")
    val BALANCE_READ_AFTER_GEN_AC: ITag =
        TagImpl("df8105", TagValueTypeEnum.BINARY, "Balance Read After Gen AC", "")
    val DATA_NEEDED: ITag = TagImpl("df8106", TagValueTypeEnum.BINARY, "Data Needed", "")
    val CDOL1_RELATED_DATA: ITag =
        TagImpl("df8107", TagValueTypeEnum.BINARY, "CDOL1 Related Data", "")
    val DS_AC_TYPE: ITag = TagImpl("df8108", TagValueTypeEnum.BINARY, "DS AC Type", "")
    val DS_INPUT_TERM: ITag = TagImpl("df8109", TagValueTypeEnum.BINARY, "DS Input (Term)", "")
    val DS_ODS_INFO_FOR_READER: ITag =
        TagImpl("df810a", TagValueTypeEnum.BINARY, "DS ODS Info For Reader", "")
    val DS_SUMMARY_STATUS: ITag =
        TagImpl("df810b", TagValueTypeEnum.BINARY, "DS Summary Status", "")
    val KERNEL_ID: ITag = TagImpl("df810c", TagValueTypeEnum.BINARY, "Kernel ID", "")
    val DSVN_TERM: ITag = TagImpl("df810d", TagValueTypeEnum.BINARY, "DSVN Term", "")
    val POST_GEN_AC_PUT_DATA_STATUS: ITag =
        TagImpl("df810e", TagValueTypeEnum.BINARY, "Post-Gen AC Put Data Status", "")
    val PRE_GEN_AC_PUT_DATA_STATUS: ITag =
        TagImpl("df810f", TagValueTypeEnum.BINARY, "Pre-Gen AC Put Data Status", "")
    val PROCEED_TO_WRITE_FIRST_FLAG: ITag =
        TagImpl("df8110", TagValueTypeEnum.BINARY, "Proceed To First Write Flag", "")
    val PDOL_RELATED_DATA: ITag =
        TagImpl("df8111", TagValueTypeEnum.BINARY, "PDOL Related Data", "")
    val TAGS_TO_READ: ITag = TagImpl("df8112", TagValueTypeEnum.BINARY, "Tags To Read", "")
    val DRDOL_RELATED_DATA: ITag =
        TagImpl("df8113", TagValueTypeEnum.BINARY, "DRDOL Related Data", "")
    val REFERENCE_CONTROL_PARAMETER: ITag =
        TagImpl("df8114", TagValueTypeEnum.BINARY, "Reference Control Parameter", "")
    val ERROR_INDICATION: ITag = TagImpl("df8115", TagValueTypeEnum.BINARY, "Error Indication", "")
    val USER_INTERFACE_REQUEST_DATA: ITag =
        TagImpl("df8116", TagValueTypeEnum.BINARY, "User Interface Request Data", "")
    val CARD_DATA_INPUT_CAPABILITY: ITag =
        TagImpl("df8117", TagValueTypeEnum.BINARY, "Card Data Input Capability", "")
    val CMV_CAPABILITY_CMV_REQUIRED: ITag =
        TagImpl("df8118", TagValueTypeEnum.BINARY, "CVM Capability - CVM Required", "")
    val CMV_CAPABILITY_NO_CMV_REQUIRED: ITag =
        TagImpl("df8119", TagValueTypeEnum.BINARY, "CVM Capability - No CVM Required", "")
    val DEFAULT_UDOL: ITag = TagImpl("df811a", TagValueTypeEnum.BINARY, "Default UDOL", "")
    val KERNEL_CONFIGURATION: ITag =
        TagImpl("df811b", TagValueTypeEnum.BINARY, "Kernel Configuration", "")
    val MAX_LIFETIME_TORN_TRANSACTION_LOG_REC: ITag = TagImpl(
        "df811c",
        TagValueTypeEnum.BINARY,
        "Max Lifetime of Torn Transaction Log Record",
        ""
    )
    val MAX_NUMBER_TORN_TRANSACTION_LOG_REC: ITag =
        TagImpl("df811d", TagValueTypeEnum.BINARY, "Max Number of Torn Transaction Log Records", "")
    val MAG_STRIPE_CMV_CAPABILITY_CMV_REQUIRED: ITag =
        TagImpl("df811e", TagValueTypeEnum.BINARY, "Mag-stripe CVM Capability – CVM Required", "")
    val SECURITY_CAPABILITY: ITag =
        TagImpl("df811f", TagValueTypeEnum.BINARY, "Security Capability", "")
    val TERMINAL_ACTION_CODE_DEFAULT: ITag =
        TagImpl("df8120", TagValueTypeEnum.BINARY, "Terminal Action Code – Default", "")
    val TERMINAL_ACTION_CODE_DENIAL: ITag =
        TagImpl("df8121", TagValueTypeEnum.BINARY, "Terminal Action Code – Denial", "")
    val TERMINAL_ACTION_CODE_ONLINE: ITag =
        TagImpl("df8122", TagValueTypeEnum.BINARY, "Terminal Action Code – Online", "")
    val READER_CONTACTLESS_FLOOR_LIMIT: ITag =
        TagImpl("df8123", TagValueTypeEnum.BINARY, "Reader Contactless Floor Limit", "")
    val READER_CL_TRANSACTION_LIMIT_NO_CMV: ITag = TagImpl(
        "df8124",
        TagValueTypeEnum.BINARY,
        "Reader Contactless Transaction Limit (No On-device CVM)",
        ""
    )
    val READER_CL_TRANSACTION_LIMIT_CVM: ITag = TagImpl(
        "df8125",
        TagValueTypeEnum.BINARY,
        "Reader Contactless Transaction Limit (On-device CVM)",
        ""
    )
    val READER_CMV_REQUIRED_LIMIT: ITag =
        TagImpl("df8126", TagValueTypeEnum.BINARY, "Reader CVM Required Limit", "")
    val TIME_OUT_VALUE: ITag = TagImpl("df8127", TagValueTypeEnum.BINARY, "TIME_OUT_VALUE", "")
    val IDS_STATUS: ITag = TagImpl("df8128", TagValueTypeEnum.BINARY, "IDS Status", "")
    val OUTCOME_PARAMETER_SET: ITag =
        TagImpl("df8129", TagValueTypeEnum.BINARY, "Outcome Parameter Set", "")
    val DD_CARD_TRACK1: ITag = TagImpl("df812a", TagValueTypeEnum.BINARY, "DD Card (Track1)", "")
    val DD_CARD_TRACK2: ITag = TagImpl("df812b", TagValueTypeEnum.BINARY, "DD Card (Track2)", "")
    val MAG_STRIPE_CMV_CAPABILITY_NO_CMV_REQ: ITag = TagImpl(
        "df812c",
        TagValueTypeEnum.BINARY,
        "Mag-stripe CVM Capability – No CVM Required",
        ""
    )
    val MESSAGE_HOLD_TIME: ITag =
        TagImpl("df812d", TagValueTypeEnum.BINARY, "Message Hold Time", "")
    val TORN_RECORD: ITag = TagImpl("ff8101", TagValueTypeEnum.BINARY, "Torn Record", "")
    val TAGS_TO_WRITE_BEFORE_GEN_AC: ITag =
        TagImpl("ff8102", TagValueTypeEnum.BINARY, "Tags To Write Before Gen AC", "")
    val TAGS_TO_WRITE_AFTER_GEN_AC: ITag =
        TagImpl("ff8103", TagValueTypeEnum.BINARY, "Tags To Write After Gen AC", "")
    val DATA_TO_SEND: ITag = TagImpl("ff8104", TagValueTypeEnum.BINARY, "Data To Send", "")
    val DATA_RECORD: ITag = TagImpl("ff8105", TagValueTypeEnum.BINARY, "Data Record", "")
    val DISCRETIONARY_DATA: ITag =
        TagImpl("ff8106", TagValueTypeEnum.BINARY, "Discretionary Data", "")
    // @formatter:on
    /**
     * If the tag is not found, this method returns the "[UNHANDLED TAG]" containing 'tagBytes'
     *
     * @param tagBytes
     * @return
     */
    @JvmStatic
    fun getNotNull(tagBytes: ByteArray?): ITag? {
        var tag = find(tagBytes)
        if (tag == null) {
            tag = createUnknownTag(tagBytes)
        }
        return tag
    }

    fun createUnknownTag(tagBytes: ByteArray?): ITag {
        return TagImpl(tagBytes, TagValueTypeEnum.BINARY, "[UNKNOWN TAG]", "")
    }

    /**
     * Returns null if Tag not found
     */
    @JvmStatic
    fun find(tagBytes: ByteArray?): ITag? {
        return tags[wrapperAround(tagBytes)]
    }

    private fun addTag(tag: ITag) {
        // Use 'wrapper around', since the underlaying byte-array will not be changed in this case
        val baw = wrapperAround(tag.tagBytes)
        require(!tags.containsKey(baw)) { "Tag already added $tag" }
        tags[baw] = tag
    }

    init {
        val fields: Array<Field?> = EmvTags::class.java.fields
        for (f in fields) {
            if (f?.type == ITag::class.java) {
                try {
                    val t = f.get(null) as ITag
                    addTag(t)
                } catch (ex: IllegalAccessException) {
                    throw RuntimeException(ex)
                }
            }
        }
    }
}