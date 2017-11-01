package com.kindfriends.freefree.data

import com.kindfriends.freefree.CoreConfigDef

/**
 * Created by qkrtndyd1234 on 2017. 10. 30..
 */

data class ProductClass(
        val productId: Int,                                     //제품 아이디
        val productName: String="",                             //제품명
        val productType: String="",                             //제품 종류
        val productImage: String="",                            //제품 이미지 경로
        val productPrice: Int,                                  //제품 가격
        val productMaxCount: Int,                               //제품 총 수량
        val productCurCount: Int=0                              //제품 현재 수량
)