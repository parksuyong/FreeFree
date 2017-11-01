package com.kindfriends.freefree.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.kindfriends.freefree.CoreConfigDef

import com.kindfriends.freefree.R
import com.kindfriends.freefree.adapter.MainSearchListAdapter
import com.kindfriends.freefree.data.*
import com.kindfriends.freefree.databinding.FragmentMainSearchBinding
import com.kindfriends.freefree.util.WeakReferenceHandler
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainSearchFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainSearchFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mdataBinding: FragmentMainSearchBinding? = null
    private var mitemList = ArrayList<MainSearchListItem>()
    private var mHandler: Messagehandler? = null
    private var mAdapter: MainSearchListAdapter? = null
    private var mGlideManager: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
//            mParam1 = arguments.getString(ARG_PARAM1)
//            mParam2 = arguments.getString(ARG_PARAM2)
        }
        initialize()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if(mdataBinding != null)
            return mdataBinding?.root

        mdataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_search,container,false)
        mdataBinding?.swipeRefreshLayout?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            mHandler?.sendEmptyMessage(CoreConfigDef.MainSearchHandlerRefreshListView)
        })

        return mdataBinding?.root
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainSearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): MainSearchFragment {
            val fragment = MainSearchFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
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
        for(i in 0..10){
            val artistInfo = ArtistClass(0,"kimya410","김먀","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg")
            val productList = ArrayList<ProductClass>()
            for(j in 0..10){
                val productItem = ProductClass(j,"오소마츠 스티커","스티커","https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1",1050,100,5)
                productList.add(productItem)
            }
            val tongpanInfo=TongPanClass(i,"8월 오소마츠 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes","http://digital-photography-school.com/wp-content/uploads/2012/10/image5.jpg",
                    "10/29","11/10",1000,15000,"오소마츠상;스티커;컵;아크릴스탠드;책",productList,artistInfo)
            val item = MainSearchListItem(null,tongpanInfo,1)
            mitemList.add(item)
        }
        var artistList = ArrayList<ArtistClass>()
        for(i in 0..10){
            val artistInfo = ArtistClass(i,"kimya410","김먀","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg")
            artistList.add(artistInfo)
        }
        val item = MainSearchListItem(artistList,null,0)
        mitemList.add(0,item)
    }

    private class Messagehandler (reference:MainSearchFragment) : WeakReferenceHandler<MainSearchFragment>(reference){
        override fun handleMessage(mReference: MainSearchFragment?, msg: Message) {
            if(mReference == null) return
            when(msg.what){
                CoreConfigDef.MainSearchHandlerFirstLoadItems -> {
                    //처음 작가 리스트 로딩
                    mReference.mdataBinding?.progressLayout?.visibility = View.VISIBLE
                    Thread(Runnable {
                        mReference.getItems()
                        mReference.mHandler?.sendEmptyMessage(CoreConfigDef.MainSearchHandlerFirstLoadEnd)

                    }).start()
                }
                CoreConfigDef.MainSearchHandlerFirstLoadEnd -> {
                    //첫 작가 리스트 로딩 끝
                    mReference.mAdapter= MainSearchListAdapter(mReference,mReference.mitemList,mReference.mGlideManager)
                    mReference.mdataBinding?.recyclerView?.layoutManager = LinearLayoutManager(mReference.context)
                    mReference.mdataBinding?.recyclerView?.adapter=mReference.mAdapter
                    mReference.mdataBinding?.progressLayout?.visibility = View.GONE
                }
                CoreConfigDef.MainSearchHandlerRefreshListView -> {
                    mReference.mAdapter= MainSearchListAdapter(mReference,mReference.mitemList,mReference.mGlideManager)
                    mReference.mdataBinding?.recyclerView?.adapter=mReference.mAdapter
                    mReference.mdataBinding?.swipeRefreshLayout?.isRefreshing = false
                }
                else -> println("not processed")
            }
        }

    }
}// Required empty public constructor
