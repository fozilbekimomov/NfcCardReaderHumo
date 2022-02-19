package uz.android.creditCardNfcReader.utils

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.tech.IsoDep
import android.nfc.tech.NfcA

/**
 * Created by pro100svitlo on 31.03.16.
 */
class CardNfcUtils(private val mActivity: Activity) {
    private val mNfcAdapter: NfcAdapter?
    private val mPendingIntent: PendingIntent
    fun disableDispatch() {
        mNfcAdapter?.disableForegroundDispatch(mActivity)
    }

    fun enableDispatch() {
        mNfcAdapter?.enableForegroundDispatch(
            mActivity,
            mPendingIntent,
            INTENT_FILTER,
            TECH_LIST
        )
    }

    companion object {
        private val INTENT_FILTER = arrayOf(
            IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED),
            IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        )
        private val TECH_LIST = arrayOf(
            arrayOf(
                NfcA::class.java.name, IsoDep::class.java.name
            )
        )
    }

    init {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(mActivity)
        mPendingIntent = PendingIntent.getActivity(
            mActivity, 0,
            Intent(mActivity, mActivity.javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
        )
    }
}