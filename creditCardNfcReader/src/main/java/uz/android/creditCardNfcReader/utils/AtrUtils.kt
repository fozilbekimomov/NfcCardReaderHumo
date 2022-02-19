package uz.android.creditCardNfcReader.utils

import android.util.Log
import org.apache.commons.collections4.MultiMap
import org.apache.commons.collections4.map.MultiValueMap
import org.apache.commons.io.IOUtils
import org.apache.commons.lang3.StringUtils
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.*

/**
 * Created by pro100svitlo on 15.05.16.
 */
object AtrUtils {
    /**
     * MultiMap containing ATR
     */
    private val MAP: MultiMap<String, String> = MultiValueMap()
    private const val TAG = "creditCardNfcReader"

    /**
     * Method used to find description from ATR
     *
     * @param pAtr Card ATR
     * @return list of description
     */
    fun getDescription(pAtr: String?): Collection<String?>? {
        var ret: Collection<String?>? = null
        if (StringUtils.isNotBlank(pAtr)) {
            val deleteWhitespace = StringUtils.deleteWhitespace(pAtr)
            for (key in MAP.keys) {
                if (deleteWhitespace.matches("^$key$".toRegex())) {
                    ret = MAP[key] as Collection<String?>?
                    break
                }
            }
        }
        return ret
    }

    /**
     * Method used to find ATR description from ATS (Answer to select)
     *
     * @param pAts EMV card ATS
     * @return card description
     */
    fun getDescriptionFromAts(pAts: String?): Collection<String?>? {
        var ret: Collection<String?>? = null
        if (StringUtils.isNotBlank(pAts)) {
            val `val` = StringUtils.deleteWhitespace(pAts)
            for (key in MAP.keys) {
                if (key.contains(`val`)) { // TODO Fix this
                    ret = MAP[key] as Collection<String?>?
                    break
                }
            }
        }
        return ret
    }

    init {
        var inputStream: InputStream? = null
        var inputStreamReader: InputStreamReader? = null
        var bufferedReader: BufferedReader? = null
        try {
            inputStream =
                AtrUtils::class.java.getResourceAsStream("/smartcard_list.txt")
            inputStreamReader = InputStreamReader(
                inputStream,
                StandardCharsets.UTF_8
            )
            bufferedReader =
                BufferedReader(inputStreamReader)
            var lineNumber = 0
            var line: String
            var currentATR: String? = null
            while (bufferedReader.readLine()
                    .also { line = it } != null
            ) {
                ++lineNumber
                if (line.startsWith("#") || line.trim { it <= ' ' }.length == 0) { // comment ^#/ empty line ^$/
                    continue
                } else if (line.startsWith("\t") && currentATR != null) {
                    MAP[currentATR] =
                        line.replace("\t", "")
                            .trim { it <= ' ' }
                } else if (line.startsWith("3")) { // ATR hex
                    currentATR =
                        StringUtils.deleteWhitespace(line.uppercase(Locale.getDefault()))
                } else {
                    Log.d(
                        TAG,
                        "Encountered unexpected line in atr list: currentATR=" + currentATR + " Line(" + lineNumber
                                + ") = " + line
                    )
                }
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        } finally {
            IOUtils.closeQuietly(bufferedReader)
            IOUtils.closeQuietly(inputStreamReader)
            IOUtils.closeQuietly(inputStream)
        }
    }
}