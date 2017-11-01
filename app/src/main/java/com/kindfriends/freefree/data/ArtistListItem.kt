package com.kindfriends.freefree.data

import com.kindfriends.freefree.CoreConfigDef

/**
 * Created by qkrtndyd1234 on 2017. 10. 22..
 */
data class ArtistListItem (
        val artistPinId: Int,                                       //고유 아이디
        val artistId: String="",                                      //아이디
        val artistName: String="",                                    //유저 이름
        val artistProfile: String="",                                 //유저 프로필 이미지 경로
        val comment: String="",                                     //소개글
        val mainImage: String="",                                   //대문 이미지 경로
        val tag: String="",                                         //태그
        val productList: ArrayList<ProductClass>?                   //제품 품목 리스트
)