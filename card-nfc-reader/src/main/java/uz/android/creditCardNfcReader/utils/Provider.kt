package uz.android.creditCardNfcReader.utils

import android.nfc.tech.IsoDep
import android.util.Log
import uz.android.creditCardNfcReader.enums.SwEnum.Companion.getSW
import uz.android.creditCardNfcReader.parser.IProvider
import java.io.IOException

class Provider : IProvider {
    val log = StringBuffer()
    private var mTagCom: IsoDep? = null
    fun setmTagCom(mTagCom: IsoDep?) {
        this.mTagCom = mTagCom
    }

    //    @Throws(CommunicationException::class)
    override fun transceive(pCommand: ByteArray?): ByteArray? {
        log.append("=================<br/>")
        log.append(
            "<font color='green'><b>send:</b> " + uz.android.creditCardNfcReader.utils.BytesUtils.bytesToString(
                pCommand
            )
        )
            .append("</font><br/>")
        var response: ByteArray? = null
        if (mTagCom!!.isConnected) {
            response = try {
                // send command to emv card
                try {
                    mTagCom?.transceive(pCommand)
                } catch (e: Exception) {
                    null
                }
            } catch (e: IOException) {
                null
            }
        }
        log.append(
            "<font color='blue'><b>resp:</b> " + uz.android.creditCardNfcReader.utils.BytesUtils.bytesToString(
                response
            )
        )
            .append("</font><br/>")
        Log.d(
            TAG,
            "resp: " + uz.android.creditCardNfcReader.utils.BytesUtils.bytesToString(response)
        )
        try {
            Log.d(TAG, "resp: " + TlvUtil.prettyPrintAPDUResponse(response))
            val anEnum = getSW(response)
            if (anEnum != null) {
                Log.d(TAG, "resp: " + anEnum.detail)
            }
            log.append("<pre>").append(
                TlvUtil.prettyPrintAPDUResponse(response).replace("\n", "<br/>")
                    .replace(" ", "&nbsp;")
            )
                .append("</pre><br/>")
        } catch (e: Exception) {
        }
        return response
    }

    companion object {
        private val TAG = Provider::class.java.name
    }
}