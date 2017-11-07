package com.kindfriends.freefree.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by qkrtndyd1234 on 2017. 10. 30..
 */

data class ProductClass(
        val artistNo: Int,
        val productNo: Int,                                     //제품 아이디
        val productName: String? = null,                             //제품명
        val thumbImage: String? = null,                            //제품 이미지 경로
        val detailImage: String? = null,                             //상세 페이지 이미지 경로
        val createDate: String? = null,
        val updateDate: String? = null,
        val discounts: ArrayList<ProductDiscountClass>? = null,
        val items: ArrayList<ProductItemsClass>? = null
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.createTypedArrayList(ProductDiscountClass.CREATOR),
            source.createTypedArrayList(ProductItemsClass.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(artistNo)
        writeInt(productNo)
        writeString(productName)
        writeString(thumbImage)
        writeString(detailImage)
        writeString(createDate)
        writeString(updateDate)
        writeTypedList(discounts)
        writeTypedList(items)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ProductClass> = object : Parcelable.Creator<ProductClass> {
            override fun createFromParcel(source: Parcel): ProductClass = ProductClass(source)
            override fun newArray(size: Int): Array<ProductClass?> = arrayOfNulls(size)
        }
    }
}