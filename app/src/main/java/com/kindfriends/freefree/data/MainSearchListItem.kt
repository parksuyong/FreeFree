package com.kindfriends.freefree.data

import com.kindfriends.freefree.CoreConfigDef
import com.kindfriends.freefree.adapter.MainSearchListAdapter

/**
 * Created by qkrtndyd1234 on 2017. 10. 31..
 */
data class MainSearchListItem(
        var artistList: ArrayList<ArtistClass>?=null,
        var tongpanData: TongPanClass?=null,
        var viewType: Int

)