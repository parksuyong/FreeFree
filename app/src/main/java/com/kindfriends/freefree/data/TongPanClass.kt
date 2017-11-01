package com.kindfriends.freefree.data

import com.kindfriends.freefree.CoreConfigDef

/**
 * Created by qkrtndyd1234 on 2017. 10. 31..
 */
data class TongPanClass(
        val tongpanId: Int,            //통판 번호
        val title: String="",          //제목
        val description: String="",    //통판 소개(설명)
        val imageUrl: String="",       //대문 이미지 경로
        val startDate: String="",      //게시 시작일
        val endDate: String="",        //게시 종료일
        val priceMin: Int,             //제품 최저 가격
        val priceMax: Int,             //제품 최고 가격
        val tag: String="",            //태그
        val productList: ArrayList<ProductClass>,     //제품 품목 리스트
        val artistInfo: ArtistClass

)