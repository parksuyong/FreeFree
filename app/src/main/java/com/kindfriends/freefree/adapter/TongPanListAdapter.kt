package com.kindfriends.freefree.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kindfriends.freefree.CoreConfigDef
import com.kindfriends.freefree.R
import com.kindfriends.freefree.data.TongPanListItem
import java.util.ArrayList

/**
 * Created by qkrtndyd1234 on 2017. 10. 8..
 */
class TongPanListAdapter(private var context: Context, private var mList: ArrayList<TongPanListItem>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val view: View
        when (viewType) {
            CoreConfigDef.TongPanListBasicView -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.list_tongpan_basic_view, parent, false)
                return ListViewHolder(view)
            }
        }

        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val object_type = getItemViewType(position)
        when (object_type) {
            CoreConfigDef.TongPanListBasicView -> {

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

    private inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    private inner class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}

