package com.kindfriends.freefree.data

import com.kindfriends.freefree.CoreConfigDef

data class TongPanListItem (
        var tongPanId: ArtistClass? = null,            //통판 작가 정보
        val userId: String="",                 //아이디
        val userProfile: String="",    //유저 프로필 이미지 경로
        val startDate: String="",      //게시 시작일
        val endDate: String="",        //게시 종료일
        val title: String="",          //제목
        val description: String="",    //설명글
        val imageUrl: String="",       //대문 이미지 경로
        val tag: String="",           //태그
        val productList: String="",    //제품 품목 리스트
        val viewType: Int=CoreConfigDef.TongPanListBasicView           //리스트 뷰 타입 (0:기본)
)
/**
 * Created by qkrtndyd1234 on 2017. 10. 8..
 */
