package uz.android.creditCardNfcReader.parser.apdu.annotation

import uz.android.creditCardNfcReader.iso7816emv.ITag
import uz.android.creditCardNfcReader.model.EmvTransactionRecord
import java.util.*

/**
 * Class used to manage all annotation
 */
class AnnotationUtils private constructor() {
    /**
     * Map which contain
     */
    private val map: MutableMap<String, Map<ITag?, AnnotationData>>
    private val mapSet: MutableMap<String, Set<AnnotationData>>

    /**
     * Method to extract all annotation information and store them in the map
     */
    private fun extractAnnotation() {
        for (clazz in LISTE_CLASS) {
            val maps: MutableMap<ITag?, AnnotationData> = HashMap()
            val set: MutableSet<AnnotationData> = TreeSet()
            val fields = clazz.declaredFields
            for (field in fields) {
                val param = AnnotationData()
                field.isAccessible = true
                param.setField(field)
                val annotation = field.getAnnotation(
                    Data::class.java
                )
                if (annotation != null) {
                    param.initFromAnnotation(annotation)
                    maps[param.tag] = param
                    try {
                        set.add(param.clone() as AnnotationData)
                    } catch (e: CloneNotSupportedException) {
                        // do nothing
                    }
                }
            }
            mapSet[clazz.name] = set
            map[clazz.name] = maps
        }
    }

    /**
     * Getter map set
     *
     * @return the map
     */
    fun getMapSet(): Map<String, Set<AnnotationData>> {
        return mapSet
    }

    /**
     * Getter map
     *
     * @return the map
     */
    fun getMap(): Map<String, Map<ITag?, AnnotationData>> {
        return map
    }

    companion object {
        /**
         * List of annoted class
         */
        private val LISTE_CLASS: Array<Class<*>> = arrayOf(
            EmvTransactionRecord::class.java
        )
        /**
         * Method to get the unique instance of the class
         *
         * @return AnnotationUtils instance
         */
        /**
         * AnnotationUtils singleton
         */
        val instance = AnnotationUtils()
    }

    /**
     * Private default constructor
     */
    init {
        map = HashMap()
        mapSet = HashMap()
        extractAnnotation()
    }
}