package uz.android.creditCardNfcReader

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.os.Handler
import android.os.Looper
import android.util.Log
import org.apache.commons.lang3.StringUtils
import uz.android.creditCardNfcReader.enums.EmvCardScheme
import uz.android.creditCardNfcReader.model.EmvCard
import uz.android.creditCardNfcReader.utils.Provider
import java.io.IOException
import java.util.concurrent.Executors

class CardNFCReaderTask private constructor(
    var b: uz.android.creditCardNfcReader.CardNFCReaderTask.Builder,
    var mInterface: uz.android.creditCardNfcReader.CardNFCReaderTask.CardNFCListener?
) {

    private var mCard: EmvCard? = null
    private var mTag: Tag? = null
    private var mProvider: Provider? = null

    private var mException = false

    init {

        mProvider = Provider()

        Log.d("CardNfcAsyncTask", "init: 1")

        mTag = b.mTag
        if (mTag != null) {
            Log.d("CardNfcAsyncTask", "init: 2")
            try {
                Log.d("CardNfcAsyncTask", "init: 3")
                if (mTag.toString() == uz.android.creditCardNfcReader.CardNFCReaderTask.Companion.NFC_A_TAG || mTag.toString() == uz.android.creditCardNfcReader.CardNFCReaderTask.Companion.NFC_B_TAG) {
                    Log.d("CardNfcAsyncTask", "init: 4")
                    executeTask()
                } else {
                    Log.d("CardNfcAsyncTask", "init: 5")
                    if (!b.intentFromCreate) {
                        Log.d("CardNfcAsyncTask", "init: 6")
                        mInterface?.unknownEmvCard()
                    }
                    clearAll()
                }
            } catch (e: NullPointerException) {
                Log.d("CardNfcAsyncTask", "init: 7")
                e.printStackTrace()
            }
        }
        Log.d("CardNfcAsyncTask", "init: 8")
    }

    private fun clearAll() {
        mInterface = null
        mProvider = null
        mCard = null
        mTag = null
    }

    private fun executeTask() {
        mInterface?.startNFCReadCard()
        mProvider?.log?.setLength(0)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executor.execute {

            doInBackground()

            handler.post {
                onPostExecute()
                executor.shutdown()
            }
        }
    }

    private fun doInBackground() {


        val mIsoDep = IsoDep.get(mTag)
        if (mIsoDep == null) {
            mInterface?.doNotMoveCardSoFast()
            return
        }
        mException = false
        Log.d("CardNfcAsyncTask", "doInBackground0: ")
        try {
            // Open connection
            mIsoDep.close()
//            if (!mIsoDep.isConnected) {
            mIsoDep.connect()
//            }
            mProvider?.setmTagCom(mIsoDep)
            val parser = uz.android.creditCardNfcReader.parser.EmvParser(mProvider, true)
            mCard = parser.readEmvCard()

            Log.d("CardNfcAsyncTask", "doInBackground1: " + mCard?.aid)

        } catch (e: IOException) {
            Log.d("CardNfcAsyncTask", "doInBackground2: $e")
            mException = true
        } finally {
//            IOUtils.closeQuietly(mIsoDep)
        }

    }

    private fun onPostExecute() {
        if (!mException) {
            if (mCard != null) {
                if (StringUtils.isNotBlank(mCard!!.cardNumber)) {
                    Log.e("mHolderLastName", "--> " + mCard!!.cardNumber)

                    mInterface?.cardIsReadyToRead(mCard!!)
                } else if (mCard!!.isNfcLocked) {
                    mInterface?.cardWithLockedNFC()
                }
            } else {
                mInterface?.unknownEmvCard()
            }
        } else {
            mInterface?.doNotMoveCardSoFast()
        }
        mInterface?.finishNFCReadCard()
        clearAll()
    }

    class Builder(
        var listener: uz.android.creditCardNfcReader.CardNFCReaderTask.CardNFCListener,
        var intent: Intent? = null,
        var intentFromCreate: Boolean = false,
        var mTag: Tag? = null
    ) {
        fun intent(intent: Intent?) = apply { this.intent = intent }
        fun intentFromCreate(intentFromCreate: Boolean) = apply {
            this.intentFromCreate = intentFromCreate
            this.mTag = intent?.getParcelableExtra(NfcAdapter.EXTRA_TAG)
        }

        fun build() = uz.android.creditCardNfcReader.CardNFCReaderTask(this, listener)
    }


    interface CardNFCListener {
        fun startNFCReadCard()

        fun cardIsReadyToRead(emvCard: EmvCard)

        fun doNotMoveCardSoFast()

        fun unknownEmvCard()

        fun cardWithLockedNFC()

        fun finishNFCReadCard()
    }

    companion object {
        val CARD_UNKNOWN = EmvCardScheme.UNKNOWN.toString()
        val CARD_VISA = EmvCardScheme.VISA.toString()
        val CARD_NAB_VISA = EmvCardScheme.NAB_VISA.toString()
        val CARD_MASTER_CARD = EmvCardScheme.MASTER_CARD.toString()
        val CARD_HUMO = EmvCardScheme.HUMO.toString()

        private const val NFC_A_TAG = "TAG: Tech [android.nfc.tech.IsoDep, android.nfc.tech.NfcA]"
        private const val NFC_B_TAG = "TAG: Tech [android.nfc.tech.IsoDep, android.nfc.tech.NfcB]"
    }
}