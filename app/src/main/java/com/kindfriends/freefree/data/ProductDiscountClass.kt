package com.kindfriends.freefree.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by qkrtndyd1234 on 2017. 11. 7..
 */
data class ProductDiscountClass(
        val productNo: Int,
        val productDiscountNo: Int,
        val discountPrice: Int,
        val title: String,
        val count: Int,
        val createDate: String,
        val updateDate: String

) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(productNo)
        writeInt(productDiscountNo)
        writeInt(discountPrice)
        writeString(title)
        writeInt(count)
        writeString(createDate)
        writeString(updateDate)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ProductDiscountClass> = object : Parcelable.Creator<ProductDiscountClass> {
            override fun createFromParcel(source: Parcel): ProductDiscountClass = ProductDiscountClass(source)
            override fun newArray(size: Int): Array<ProductDiscountClass?> = arrayOfNulls(size)
        }
    }
}