package uz.android.creditCardNfcReader.utils

import fr.devnied.bitlib.BytesUtils
import org.slf4j.LoggerFactory
import uz.android.creditCardNfcReader.iso7816emv.EmvTags
import uz.android.creditCardNfcReader.model.EmvCard
import uz.android.creditCardNfcReader.model.Service
import uz.android.creditCardNfcReader.utils.TlvUtil.getValue
import java.util.regex.Pattern

/**
 * Extract track data
 */
object TrackUtils {
    /**
     * Class logger
     */
    private val LOGGER = LoggerFactory.getLogger(TrackUtils::class.java)

    /**
     * Track 2 pattern
     */
    private val TRACK2_PATTERN = Pattern.compile("([0-9]{1,19})D([0-9]{4})([0-9]{3})?(.*)")

    /**
     * Extract track 2 data
     *
     * @param pEmvCard
     * Object card representation
     * @param pData
     * data to parse
     * @return true if the extraction succeed false otherwise
     */
    @JvmStatic
    fun extractTrack2Data(pEmvCard: EmvCard, pData: ByteArray?): Boolean {
        var ret = false
        val track2 = getValue(pData, EmvTags.TRACK_2_EQV_DATA, EmvTags.TRACK2_DATA)
        if (track2 != null) {
            val data = BytesUtils.bytesToStringNoSpace(track2)
            val m = TRACK2_PATTERN.matcher(data)
            // Check pattern
            if (m.find()) {
                // read card number
                pEmvCard.cardNumber = m.group(1)
                // Read expire date
                val month = m.group(2).substring(2, 4)
                val year = m.group(2).substring(0, 2)
                pEmvCard.expireDate = "$month/$year"
                // Read service
                pEmvCard.service = Service(m.group(3))
                ret = true
            }
        }
        return ret
    }
}