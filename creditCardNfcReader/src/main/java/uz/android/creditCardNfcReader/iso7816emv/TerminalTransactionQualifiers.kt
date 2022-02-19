package uz.android.creditCardNfcReader.iso7816emv

import java.util.*

/**
 * This implementation is a mix of EMV & VISA TTQ
 *
 * VISA: Terminal Transaction Qualifiers (Tag '9F66') is a reader data element indicating capabilities (e.g., MSD or qVSDC) and
 * transaction-specific requirements (e.g., online) of the reader. It is requested by the card in the PDOL and used by the card to
 * determine how to process the transaction (for example, process using MSD or qVSDC, process offline or online).
 *
 */
class TerminalTransactionQualifiers {
    private val data = ByteArray(4)
    fun contactlessMagneticStripeSupported(): Boolean {
        return uz.android.creditCardNfcReader.utils.BytesUtils.matchBitByBitIndex(
            data[0].toInt(), 7
        )
    }

    fun contactlessVSDCsupported(): Boolean {
        return uz.android.creditCardNfcReader.utils.BytesUtils.matchBitByBitIndex(
            data[0].toInt(), 6
        )
    }

    fun contactlessEMVmodeSupported(): Boolean {
        return uz.android.creditCardNfcReader.utils.BytesUtils.matchBitByBitIndex(
            data[0].toInt(), 5
        )
    }

    fun contactEMVsupported(): Boolean {
        return uz.android.creditCardNfcReader.utils.BytesUtils.matchBitByBitIndex(
            data[0].toInt(), 4
        )
    }

    fun readerIsOfflineOnly(): Boolean {
        return uz.android.creditCardNfcReader.utils.BytesUtils.matchBitByBitIndex(
            data[0].toInt(), 3
        )
    }

    fun onlinePINsupported(): Boolean {
        return uz.android.creditCardNfcReader.utils.BytesUtils.matchBitByBitIndex(
            data[0].toInt(), 2
        )
    }

    fun signatureSupported(): Boolean {
        return uz.android.creditCardNfcReader.utils.BytesUtils.matchBitByBitIndex(
            data[0].toInt(), 1
        )
    }

    fun onlineCryptogramRequired(): Boolean {
        return uz.android.creditCardNfcReader.utils.BytesUtils.matchBitByBitIndex(
            data[1].toInt(), 7
        )
    }

    fun cvmRequired(): Boolean {
        return uz.android.creditCardNfcReader.utils.BytesUtils.matchBitByBitIndex(
            data[1].toInt(), 6
        )
    }

    fun contactChipOfflinePINsupported(): Boolean {
        return uz.android.creditCardNfcReader.utils.BytesUtils.matchBitByBitIndex(
            data[1].toInt(), 5
        )
    }

    fun issuerUpdateProcessingSupported(): Boolean {
        return uz.android.creditCardNfcReader.utils.BytesUtils.matchBitByBitIndex(
            data[2].toInt(), 7
        )
    }

    fun consumerDeviceCVMsupported(): Boolean {
        return uz.android.creditCardNfcReader.utils.BytesUtils.matchBitByBitIndex(
            data[2].toInt(), 6
        )
    }

    fun setContactlessMagneticStripeSupported(value: Boolean) {
        data[0] = uz.android.creditCardNfcReader.utils.BytesUtils.setBit(data[0], 7, value)
    }

    fun setContactlessVSDCsupported(value: Boolean) {
        data[0] = uz.android.creditCardNfcReader.utils.BytesUtils.setBit(data[0], 6, value)
        if (value) {
            /*
			 * A reader that supports contactless VSDC in addition to qVSDC shall not indicate support for qVSDC in the Terminal
			 * Transaction Qualifiers (set byte 1 bit 6 to b'0'). The reader shall restore this bit to b'1' prior to deactivation
			 */
            setContactlessEMVmodeSupported(false)
        }
    }

    fun setContactlessEMVmodeSupported(value: Boolean) {
        data[0] = uz.android.creditCardNfcReader.utils.BytesUtils.setBit(data[0], 5, value)
    }

    fun setContactEMVsupported(value: Boolean) {
        data[0] = uz.android.creditCardNfcReader.utils.BytesUtils.setBit(data[0], 4, value)
    }

    fun setReaderIsOfflineOnly(value: Boolean) {
        data[0] = uz.android.creditCardNfcReader.utils.BytesUtils.setBit(data[0], 3, value)
    }

    fun setOnlinePINsupported(value: Boolean) {
        data[0] = uz.android.creditCardNfcReader.utils.BytesUtils.setBit(data[0], 2, value)
    }

    fun setSignatureSupported(value: Boolean) {
        data[0] = uz.android.creditCardNfcReader.utils.BytesUtils.setBit(data[0], 1, value)
    }

    fun setOnlineCryptogramRequired(value: Boolean) {
        data[1] = uz.android.creditCardNfcReader.utils.BytesUtils.setBit(data[1], 7, value)
    }

    fun setCvmRequired(value: Boolean) {
        data[1] = uz.android.creditCardNfcReader.utils.BytesUtils.setBit(data[1], 6, value)
    }

    fun setContactChipOfflinePINsupported(value: Boolean) {
        data[1] = uz.android.creditCardNfcReader.utils.BytesUtils.setBit(data[1], 5, value)
    }

    fun setIssuerUpdateProcessingSupported(value: Boolean) {
        data[2] = uz.android.creditCardNfcReader.utils.BytesUtils.setBit(data[2], 7, value)
    }

    fun setConsumerDeviceCVMsupported(value: Boolean) {
        data[2] = uz.android.creditCardNfcReader.utils.BytesUtils.setBit(data[2], 6, value)
    }

    // The rest of the bits in the second byte are RFU (Reserved for Future Use)
    val bytes: ByteArray
        get() = Arrays.copyOf(data, data.size)
}