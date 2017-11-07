package com.kindfriends.freefree.adapter

import android.databinding.DataBindingUtil
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kindfriends.freefree.R
import com.kindfriends.freefree.data.MainSearchListItem
import com.kindfriends.freefree.databinding.ListSearchArtistViewBinding
import com.kindfriends.freefree.databinding.ListSearchTongpanViewBinding
import com.kindfriends.freefree.view.MainSearchFragment
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
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
                val artistItem=tongpanItem?.artist
                var mainGlideOptions = RequestOptions()
                mGlideManager?.load(tongpanItem?.mainImage)
                        ?.apply(mainGlideOptions)
                        ?.into(TongPanViewHolder(holder.itemView).dataBinding.mainImage)
                var profileGlideOptions = RequestOptions()
                profileGlideOptions.transform(CircleCrop())
                mGlideManager?.load(artistItem?.artistProfile)
                        ?.apply(profileGlideOptions)
                        ?.into(TongPanViewHolder(holder.itemView).dataBinding.artistImage)

                TongPanViewHolder(holder.itemView).dataBinding.artistName?.setText(artistItem?.artistName)
                TongPanViewHolder(holder.itemView).dataBinding.date?.setText("~"+tongpanItem?.endDate)
                TongPanViewHolder(holder.itemView).dataBinding.title?.setText(tongpanItem?.title)

                val artistPinId=artistItem?.artistNo
                ViewCompat.setTransitionName(TongPanViewHolder(holder.itemView).dataBinding.artistImage, "TONGPANLIST $artistPinId")

                val tongpanId=tongpanItem?.tongNo
                ViewCompat.setTransitionName(TongPanViewHolder(holder.itemView).dataBinding.mainImage, "TONGPANLIST $tongpanId")

                TongPanViewHolder(holder.itemView).dataBinding.root.setOnClickListener(View.OnClickListener {
                    if (tongpanItem != null) {
                        mActivity.onClickTongPanItem(tongpanItem,TongPanViewHolder(holder.itemView).dataBinding.mainImage)
                    }
                })
                TongPanViewHolder(holder.itemView).dataBinding.artistImage.setOnClickListener(View.OnClickListener {
                    if(artistItem != null)
                        mActivity.onClickArtistItem(artistItem,TongPanViewHolder(holder.itemView).dataBinding.artistImage)
                })
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