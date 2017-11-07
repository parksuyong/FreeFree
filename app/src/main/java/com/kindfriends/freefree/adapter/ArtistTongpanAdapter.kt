package com.kindfriends.freefree.adapter

import android.databinding.DataBindingUtil
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.kindfriends.freefree.R
import com.kindfriends.freefree.data.TongPanClass
import com.kindfriends.freefree.databinding.ListArtistTongpanItemBinding
import com.kindfriends.freefree.view.ArtistActivity
import java.util.ArrayList

/**
 * Created by qkrtndyd1234 on 2017. 11. 2..
 */
class ArtistTongpanAdapter(private var mActivity: ArtistActivity,
                            private var mList: ArrayList<TongPanClass>?,
                            private var mGlideManager: RequestManager?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_artist_tongpan_item, parent, false)
        return TongPanViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tongpanItem=mList?.get(position)
        var mainGlideOptions = RequestOptions()
        mGlideManager?.load(tongpanItem?.mainImage)
                ?.apply(mainGlideOptions)
                ?.into(TongPanViewHolder(holder.itemView).dataBinding.mainImage)

        TongPanViewHolder(holder.itemView).dataBinding.date?.setText("~"+tongpanItem?.endDate)
        TongPanViewHolder(holder.itemView).dataBinding.title?.setText(tongpanItem?.title)

        val tongpanId=tongpanItem?.tongNo
        ViewCompat.setTransitionName(TongPanViewHolder(holder.itemView).dataBinding.mainImage, "TONGPANLIST $tongpanId")

        TongPanViewHolder(holder.itemView).dataBinding.root.setOnClickListener(View.OnClickListener {
            if(tongpanItem != null)
                mActivity.onClickArtistItem(tongpanItem,TongPanViewHolder(holder.itemView).dataBinding.mainImage)
        })

    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    private inner class TongPanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var dataBinding: ListArtistTongpanItemBinding = DataBindingUtil.bind(itemView)

    }


}