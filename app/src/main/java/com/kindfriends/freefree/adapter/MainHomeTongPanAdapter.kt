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
import com.kindfriends.freefree.databinding.ListHomeTongpanItemBinding
import com.kindfriends.freefree.view.MainHomeFragment

/**
 * Created by qkrtndyd1234 on 2017. 11. 3..
 */
class MainHomeTongPanAdapter(private var mActivity: MainHomeFragment,
                             private var mList: ArrayList<TongPanClass>?,
                             private var mGlideManager: RequestManager?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_home_tongpan_item, parent, false)
        return TongPanViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tongpanItem=mList?.get(position)
        var mainGlideOptions = RequestOptions()
        mGlideManager?.load(tongpanItem?.mainImage)
                ?.apply(mainGlideOptions)
                ?.into(TongPanViewHolder(holder.itemView).dataBinding.tongImage)

        TongPanViewHolder(holder.itemView).dataBinding.date?.setText("~"+tongpanItem?.endDate)
        TongPanViewHolder(holder.itemView).dataBinding.title?.setText(tongpanItem?.title)

        val tongpanId=tongpanItem?.tongNo
        ViewCompat.setTransitionName(TongPanViewHolder(holder.itemView).dataBinding.tongImage, "TONGPANLIST $tongpanId")

        TongPanViewHolder(holder.itemView).dataBinding.root.setOnClickListener(View.OnClickListener {
//            mActivity.onClickArtistItem(position,TongPanViewHolder(holder.itemView).dataBinding.mainImage)
        })

    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    private inner class TongPanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var dataBinding: ListHomeTongpanItemBinding = DataBindingUtil.bind(itemView)

    }


}