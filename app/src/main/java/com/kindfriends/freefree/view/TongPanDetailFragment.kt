package com.kindfriends.freefree.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Message
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.kindfriends.freefree.CoreConfigDef
import com.kindfriends.freefree.R
import com.kindfriends.freefree.adapter.MainHomeTongPanAdapter
import com.kindfriends.freefree.data.ArtistClass
import com.kindfriends.freefree.data.TongPanClass
import com.kindfriends.freefree.databinding.FragmentMainHomeBinding
import com.kindfriends.freefree.util.WeakReferenceHandler
import java.util.ArrayList

/**
 * Created by qkrtndyd1234 on 2017. 11. 11..
 */
class TongPanDetailFragment : Fragment() {

    private var mdataBinding: FragmentMainHomeBinding? = null
    private var mTongpan :TongPanClass? = null
    private var mHandler: Messagehandler? = null
    private var mTongPanAdapter: MainHomeTongPanAdapter? = null
    private var mGlideManager: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mTongpan=arguments.getParcelable<TongPanClass>("tongpan")
            if(mTongpan == null){
                //오류 팝업
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if(mdataBinding != null)
            return mdataBinding?.root


        mdataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_home,container,false)
        initialize()

        return mdataBinding?.root
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainHomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(mTongPan: TongPanClass): MainHomeFragment {
            val fragment = MainHomeFragment()
            val args = Bundle()
            args.putParcelable("tongpan",mTongPan)
            fragment.arguments = args
            return fragment
        }
    }
    private fun initialize(){
        mGlideManager = Glide.with(this)
        if(mHandler == null)
            mHandler = Messagehandler(this)


        mHandler?.sendEmptyMessage(CoreConfigDef.MainSearchHandlerFirstLoadItems)
    }

    fun onClickTongPanItem(tongPanItem: TongPanClass, onImageView: View){
        val intent = Intent(context, TongPanActivity::class.java)
        intent.putExtra("tongpan", tongPanItem)
        intent.putExtra("image_transition_name", ViewCompat.getTransitionName(onImageView))

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                onImageView,
                ViewCompat.getTransitionName(onImageView))
        startActivityForResult(intent, CoreConfigDef.ArtistIntentResultCodeTongPanActivity ,options.toBundle())
    }


    inner class Messagehandler (reference:MainHomeFragment) : WeakReferenceHandler<MainHomeFragment>(reference){
        override fun handleMessage(mReference: MainHomeFragment?, msg: Message) {
            if(mReference == null) return
            when(msg.what){
                CoreConfigDef.MainHomeHandlerFirstLoadItems -> {
                    //처음 작가 리스트 로딩
                    mReference.mdataBinding?.progressLayout?.visibility = View.VISIBLE
                    Thread(Runnable {
                        mReference.getItems()
                        mReference.mHandler?.sendEmptyMessage(CoreConfigDef.MainSearchHandlerFirstLoadEnd)

                    }).start()
                }
                CoreConfigDef.MainHomeHandlerFirstLoadEnd -> {
                    //첫 작가 리스트 로딩 끝
                    mReference.mTongPanAdapter= MainHomeTongPanAdapter(mReference,mReference.mTongpanList,mReference.mGlideManager)
                    mReference.mdataBinding?.tongpanListView?.layoutManager = LinearLayoutManager(mReference.context)
                    mReference.mdataBinding?.tongpanListView?.adapter=mReference.mTongPanAdapter
                    mReference.mdataBinding?.progressLayout?.visibility = View.GONE
                }
                CoreConfigDef.MainSearchHandlerRefreshListView -> {
//                    mReference.mAdapter= MainSearchListAdapter(mReference,mReference.mitemList,mReference.mGlideManager)
//                    mReference.mdataBinding?.recyclerView?.adapter=mReference.mAdapter
//                    mReference.mdataBinding?.swipeRefreshLayout?.isRefreshing = false
                }
                else -> println("not processed")
            }
        }

    }

}