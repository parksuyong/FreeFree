package com.kindfriends.freefree.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.kindfriends.freefree.R
import com.kindfriends.freefree.data.MainSearchListItem
import com.kindfriends.freefree.databinding.ListSearchArtistViewBinding
import com.kindfriends.freefree.databinding.ListSearchTongpanViewBinding
import com.kindfriends.freefree.view.MainSearchFragment
import java.util.ArrayList

/**
 * Created by qkrtndyd1234 on 2017. 10. 22..
 */
class MainSearchListAdapter(private var mActivity: MainSearchFragment,
                            private var mList: ArrayList<MainSearchListItem>?,
                            private var mGlideManager: RequestManager?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val MAIN_SEARCH_LIST_ITEM_VIEW_ARTIST: Int = 0
    val MAIN_SEARCH_LIST_ITEM_VIEW_TONGPAN: Int = 1
    val MAIN_SEARCH_LIST_ITEM_VIEW_AD: Int = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val view: View
        when (viewType) {
            MAIN_SEARCH_LIST_ITEM_VIEW_ARTIST -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.list_search_artist_view, parent, false)
                return ArtistViewHolder(view)
            }
            MAIN_SEARCH_LIST_ITEM_VIEW_TONGPAN -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.list_search_tongpan_view, parent, false)
                return TongPanViewHolder(view)
            }

        }

        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val object_type = getItemViewType(position)
        when (object_type) {
            MAIN_SEARCH_LIST_ITEM_VIEW_ARTIST -> {
                val mAdapter= MainSearchListArtistAdapter(mActivity,mList?.get(position)?.artistList,mGlideManager)
                val layoutManager = LinearLayoutManager(mActivity.context)
                layoutManager.orientation=LinearLayoutManager.HORIZONTAL
                ArtistViewHolder(holder.itemView).dataBinding.artistRecyclerView.layoutManager = layoutManager
                ArtistViewHolder(holder.itemView).dataBinding.artistRecyclerView.adapter=mAdapter

            }
            MAIN_SEARCH_LIST_ITEM_VIEW_TONGPAN -> {

                val tongpanItem=mList?.get(position)?.tongpanData
                val artistItem=tongpanItem?.artistInfo
                var mainGlideOptions = RequestOptions()
                mGlideManager?.load(tongpanItem?.imageUrl)
                        ?.apply(mainGlideOptions)
                        ?.into(TongPanViewHolder(holder.itemView).dataBinding.mainImage)
                var progileGlideOptions = RequestOptions()
                mGlideManager?.load(artistItem?.artistProfile)
                        ?.apply(progileGlideOptions)
                        ?.into(TongPanViewHolder(holder.itemView).dataBinding.artistImage)
                val minPrice = tongpanItem?.priceMin
                val maxPrice = tongpanItem?.priceMax

                TongPanViewHolder(holder.itemView).dataBinding.artistName?.setText(artistItem?.artistName)
                TongPanViewHolder(holder.itemView).dataBinding.date?.setText("~"+tongpanItem?.endDate)
                TongPanViewHolder(holder.itemView).dataBinding.title?.setText(tongpanItem?.title)
                TongPanViewHolder(holder.itemView).dataBinding.priceRange?.setText("$minPrice ~  $maxPrice")
            }
        }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        if (mList != null) {
            val mmObject = mList?.get(position)
            if (mmObject != null) {
                return mmObject.viewType
            }
        }
        return -1
    }

    private inner class TongPanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var dataBinding: ListSearchTongpanViewBinding = DataBindingUtil.bind(itemView)

    }
    private inner class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var dataBinding: ListSearchArtistViewBinding = DataBindingUtil.bind(itemView)

    }
    private inner class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}