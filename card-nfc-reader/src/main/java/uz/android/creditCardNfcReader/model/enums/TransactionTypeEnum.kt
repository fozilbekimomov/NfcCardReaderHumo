package uz.android.creditCardNfcReader.model.enums

/**
 * Transaction type
 *
 */
enum class TransactionTypeEnum
/**
 * Constructor using field
 *
 * @param value
 */(
    /**
     * Value
     */
    override val key: Int
) : IKeyEnum {
    /**
     * '00' for a purchase transaction
     */
    PURCHASE(0x00),

    /**
     * '01' Cach advance
     */
    CASH_ADVANCE(0x01),

    /**
     * '09' for a purchase with cashback
     */
    CASHBACK(0x09),

    /**
     * '20' for a refund transaction
     */
    REFUND(0x20);

}