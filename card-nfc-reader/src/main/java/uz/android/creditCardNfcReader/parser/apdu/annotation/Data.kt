package uz.android.creditCardNfcReader.parser.apdu.annotation

import fr.devnied.bitlib.BitUtils

/**
 * Annotation to describe field information
 *
 */
@Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Data(
    /**
     * Format date
     */
    val format: String = BitUtils.DATE_FORMAT,
    /**
     * The current date standard
     */
    val dateStandard: Int = 0,
    /**
     * index of data
     */
    val index: Int,
    /**
     * Read the string in hexa
     */
    val readHexa: Boolean = false,
    /**
     * Number of bytes
     */
    val size: Int,
    /**
     * Tag Name
     */
    val tag: String
)