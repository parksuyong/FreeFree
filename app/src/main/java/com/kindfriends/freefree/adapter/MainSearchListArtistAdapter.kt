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
import com.kindfriends.freefree.data.ArtistClass
import com.kindfriends.freefree.databinding.ListSearchArtistViewItemBinding
import com.kindfriends.freefree.view.MainSearchFragment

/**
 * Created by qkrtndyd1234 on 2017. 11. 1..
 */
class MainSearchListArtistAdapter(private var mActivity: MainSearchFragment,
                                  private var mList: ArrayList<ArtistClass>?,
                                  private var mGlideManager: RequestManager?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val view: View
        when (viewType) {
            0 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.list_search_artist_view_item, parent, false)
                return ArtistViewHolder(view)
            }
        }

        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val object_type = getItemViewType(position)
        when (object_type) {
            0 -> {
                val artistItem=mList?.get(position)
                var progileGlideOptions = RequestOptions()
                progileGlideOptions.override(80,80)
                mGlideManager?.load(artistItem?.artistProfile)
                        ?.apply(progileGlideOptions)
                        ?.into(ArtistViewHolder(holder.itemView).dataBinding.artistImage)

                ArtistViewHolder(holder.itemView).dataBinding.artistName?.setText(artistItem?.artistName)


            }
        }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        if (mList != null) {
            return 0
        }
        return -1
    }

    private inner class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var dataBinding: ListSearchArtistViewItemBinding = DataBindingUtil.bind(itemView)

    }


}