package com.kindfriends.freefree.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by qkrtndyd1234 on 2017. 10. 31..
 */
data class ArtistClass(
        val artistNo: Int,                                       //고유 아이디
        val artistId: String,                                      //아이디
        val artistPw: String? = null,
        val artistName: String,                                    //유저 이름
        val artistProfile: String? = null,                                 //유저 프로필 이미지 경로
        val comment: String? = null,                                     //소개글
        val mainImage: String? = null,                                   //대문 이미지 경로
        val tag: String? = null,                                        //태그
        val createDate: String,
        val updateDate: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(artistNo)
        writeString(artistId)
        writeString(artistPw)
        writeString(artistName)
        writeString(artistProfile)
        writeString(comment)
        writeString(mainImage)
        writeString(tag)
        writeString(createDate)
        writeString(updateDate)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ArtistClass> = object : Parcelable.Creator<ArtistClass> {
            override fun createFromParcel(source: Parcel): ArtistClass = ArtistClass(source)
            override fun newArray(size: Int): Array<ArtistClass?> = arrayOfNulls(size)
        }
    }
}