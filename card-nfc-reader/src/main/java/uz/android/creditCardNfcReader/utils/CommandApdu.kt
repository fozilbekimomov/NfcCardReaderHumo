package uz.android.creditCardNfcReader.utils

import uz.android.creditCardNfcReader.enums.CommandEnum

class CommandApdu {
    protected var mCla = 0x00
    protected var mIns = 0x00
    protected var mP1 = 0x00
    protected var mP2 = 0x00
    protected var mLc = 0x00
    protected var mData: ByteArray? = ByteArray(0)
    protected var mLe = 0x00
    protected var mLeUsed = false

    constructor(pEnum: CommandEnum, data: ByteArray?, le: Int) {
        mCla = pEnum.cla
        mIns = pEnum.ins
        mP1 = pEnum.p1
        mP2 = pEnum.p2
        mLc = data?.size ?: 0
        mData = data
        mLe = le
        mLeUsed = true
    }

    constructor(pEnum: CommandEnum, p1: Int, p2: Int, le: Int) {
        mCla = pEnum.cla
        mIns = pEnum.ins
        mP1 = p1
        mP2 = p2
        mLe = le
        mLeUsed = true
    }

    constructor(pEnum: CommandEnum, p1: Int, p2: Int) {
        mCla = pEnum.cla
        mIns = pEnum.ins
        mP1 = p1
        mP2 = p2
        mLeUsed = false
    }

    fun toBytes(): ByteArray {
        var length = 4 // CLA, INS, P1, P2
        if (mData != null && this.mData!!.isNotEmpty()) {
            length += 1 // LC
            length += mData!!.size // DATA
        }
        if (mLeUsed) {
            length += 1 // LE
        }
        val apdu = ByteArray(length)
        var index = 0
        apdu[index] = mCla.toByte()
        index++
        apdu[index] = mIns.toByte()
        index++
        apdu[index] = mP1.toByte()
        index++
        apdu[index] = mP2.toByte()
        index++
        if (mData != null && mData!!.isNotEmpty()) {
            apdu[index] = mLc.toByte()
            index++
            System.arraycopy(mData, 0, apdu, index, mData!!.size)
            index += mData!!.size
        }
        if (mLeUsed) {
            apdu[index] = (apdu[index] + mLe.toByte()).toByte() // LE
        }
        return apdu
    }
}