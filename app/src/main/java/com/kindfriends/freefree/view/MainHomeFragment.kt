package com.kindfriends.freefree.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.kindfriends.freefree.CoreConfigDef

import com.kindfriends.freefree.R
import com.kindfriends.freefree.adapter.MainHomeTongPanAdapter
import com.kindfriends.freefree.adapter.MainSearchListAdapter
import com.kindfriends.freefree.data.*
import com.kindfriends.freefree.databinding.FragmentMainHomeBinding
import com.kindfriends.freefree.databinding.FragmentMainSearchBinding
import com.kindfriends.freefree.util.WeakReferenceHandler
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainHomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainHomeFragment : Fragment() {

    private var mdataBinding: FragmentMainHomeBinding? = null
    private var mTongpanList = ArrayList<TongPanClass>()
    private var mHandler: Messagehandler? = null
    private var mTongPanAdapter: MainHomeTongPanAdapter? = null
    private var mGlideManager: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
        initialize()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if(mdataBinding != null)
            return mdataBinding?.root

        mdataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_home,container,false)
//        mdataBinding?.swipeRefreshLayout?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
//            mHandler?.sendEmptyMessage(CoreConfigDef.MainSearchHandlerRefreshListView)
//        })

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
        fun newInstance(param1: String, param2: String): MainHomeFragment {
            val fragment = MainHomeFragment()
            val args = Bundle()
//            args.putString(ARG_PARAM1, param1)
//            args.putString(ARG_PARAM2, param2)
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

    private fun getItems(){
        val mArtistInfo = ArtistClass(0,"kimya410",null,"김먀","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","오소마츠 입덕","http://digital-photography-school.com/wp-content/uploads/2012/10/image5.jpg",null,"","")
        for(i in 0..10){
            val tongpanInfo=TongPanClass(mArtistInfo,i,"8월 오소마츠 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes","http://digital-photography-school.com/wp-content/uploads/2012/10/image5.jpg",
                    "오소마츠상;스티커;컵;아크릴스탠드;책",-1,"10/29","11/10","","",null,null)
            mTongpanList?.add(tongpanInfo)
        }

    }

    private class Messagehandler (reference:MainHomeFragment) : WeakReferenceHandler<MainHomeFragment>(reference){
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
                    mReference.mdataBinding?.tongpanListView?.layoutManager = GridLayoutManager(mReference.context,2)
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

}// Required empty public constructor
