package com.kindfriends.freefree.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by qkrtndyd1234 on 2017. 11. 7..
 */
data class TongPanDeliveryClass(
        val tongNo: Int,
        val deliveryNo: Int,
        val deliveryType: String,
        val deliveryPrice: Int

) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(tongNo)
        writeInt(deliveryNo)
        writeString(deliveryType)
        writeInt(deliveryPrice)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TongPanDeliveryClass> = object : Parcelable.Creator<TongPanDeliveryClass> {
            override fun createFromParcel(source: Parcel): TongPanDeliveryClass = TongPanDeliveryClass(source)
            override fun newArray(size: Int): Array<TongPanDeliveryClass?> = arrayOfNulls(size)
        }
    }
}