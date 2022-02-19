package uz.android.creditCardNfcReader.iso7816emv

import uz.android.creditCardNfcReader.enums.TagTypeEnum
import uz.android.creditCardNfcReader.enums.TagValueTypeEnum

interface ITag {
    enum class Class {
        UNIVERSAL, APPLICATION, CONTEXT_SPECIFIC, PRIVATE
    }

    val isConstructed: Boolean
    val tagBytes: ByteArray?
    val name: String?
    val description: String?
    val type: TagTypeEnum?
    val tagValueType: TagValueTypeEnum?
    val tagClass: Class?
    val numTagBytes: Int
}