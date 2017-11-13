package com.kindfriends.freefree.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by qkrtndyd1234 on 2017. 10. 30..
 */

data class ProductClass(
        var artistNo: Int,
        var productNo: Int,                                     //제품 아이디
        var productName: String? = null,                             //제품명
        var thumbImage: String? = null,                            //제품 이미지 경로
        var detailImage: String? = null,                             //상세 페이지 이미지 경로
        var createDate: String? = null,
        var updateDate: String? = null,
        var tongSeqNo: Int=-1,
        var tongSeq: Int=-1,
        var discounts: ArrayList<ProductDiscountClass>? = null,
        var items: ArrayList<ProductItemsClass>? = null
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt(),
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
        writeInt(tongSeqNo)
        writeInt(tongSeq)
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