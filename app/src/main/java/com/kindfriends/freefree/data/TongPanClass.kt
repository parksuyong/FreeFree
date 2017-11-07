package com.kindfriends.freefree.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by qkrtndyd1234 on 2017. 10. 31..
 */
data class TongPanClass(
        val artist: ArtistClass? = null,
        val tongNo: Int,             //통판 번호
        val title: String?,          //제목
        val description: String?,    //통판 소개(설명)
        val mainImage: String?,      //대문 이미지 경로
        val tag: String?,            //태그
        val deliveryFree: Int = -1,    //무료 배송 적용 금액 : -1: 없음, 0: 무조건 무료배송 그외: 해당 금액 이상 구매시 무료배송
        val startDate: String?,      //게시 시작일
        val endDate: String?,        //게시 종료일
        val createDate: String?,
        val updateDate: String?,
        val deliverys: ArrayList<TongPanDeliveryClass>? = null,
        val products: ArrayList<ProductClass>? = null
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readParcelable<ArtistClass>(ArtistClass::class.java.classLoader),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.createTypedArrayList(TongPanDeliveryClass.CREATOR),
            source.createTypedArrayList(ProductClass.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(artist, 0)
        writeInt(tongNo)
        writeString(title)
        writeString(description)
        writeString(mainImage)
        writeString(tag)
        writeInt(deliveryFree)
        writeString(startDate)
        writeString(endDate)
        writeString(createDate)
        writeString(updateDate)
        writeTypedList(deliverys)
        writeTypedList(products)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TongPanClass> = object : Parcelable.Creator<TongPanClass> {
            override fun createFromParcel(source: Parcel): TongPanClass = TongPanClass(source)
            override fun newArray(size: Int): Array<TongPanClass?> = arrayOfNulls(size)
        }
    }
}