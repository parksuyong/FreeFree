package com.kindfriends.freefree

/**
 * Created by qkrtndyd1234 on 2017. 10. 12..
 */

object CoreConfigDef {
    val TongPanListBasicView = 0   //통판 화면 리스트 기본뷰
    val TongPanListRecentView = 1 //최근 목록

    //메인 Activity
    val MainMessageHandlerSplashStart =0   //스플래쉬 시작
    val MainMessageHandlerSplashEnd =1   //스플래쉬 종료
    val MainMessageHandlerTongpanLoad =2   //통판 리스트 불러오기

    //홈 Fragment
    val MainHomeHandlerFirstLoadItems = 0
    val MainHomeHandlerFirstLoadEnd = 1   //초기 작가 리스트 로딩 끝

    //검색 Fragment
    val MainSearchHandlerFirstLoadItems = 0 //작가 리스트 로딩 초기
    val MainSearchHandlerFirstLoadEnd = 1   //초기 작가 리스트 로딩 끝
    val MainSearchHandlerRefreshListView = 2 //리스트 위로 당기

    //작가 Activity
    val ArtistHandlerFirstLoadItems = 1

    //통판 Activity
    val TongPanMainHandlerInitialize = 0


    val ArtistIntentResultCodeTongPanActivity = 0
}
