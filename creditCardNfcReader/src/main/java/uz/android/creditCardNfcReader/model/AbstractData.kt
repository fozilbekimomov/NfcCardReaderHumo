package uz.android.creditCardNfcReader.model

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * Abstract class used to provide some commons methods to bean
 */
abstract class AbstractData : Serializable {
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }

    companion object {
        /**
         * Generated serial UID
         */
        private const val serialVersionUID = -456811026151402726L
    }
}