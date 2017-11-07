package com.kindfriends.freefree.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by qkrtndyd1234 on 2017. 11. 7..
 */
data class ProductItemsClass(
        val productNo: Int,
        val productItemNo: Int,
        val productPrice: Int,
        val title: String,
        val remainCnt: Int
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(productNo)
        writeInt(productItemNo)
        writeInt(productPrice)
        writeString(title)
        writeInt(remainCnt)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ProductItemsClass> = object : Parcelable.Creator<ProductItemsClass> {
            override fun createFromParcel(source: Parcel): ProductItemsClass = ProductItemsClass(source)
            override fun newArray(size: Int): Array<ProductItemsClass?> = arrayOfNulls(size)
        }
    }
}