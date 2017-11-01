package com.kindfriends.freefree.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.kindfriends.freefree.CoreConfigDef

import com.kindfriends.freefree.R
import com.kindfriends.freefree.adapter.MainPagerAdapter
import com.kindfriends.freefree.databinding.ActivityMainBinding
import com.kindfriends.freefree.util.WeakReferenceHandler

class MainActivity : AppCompatActivity() {
    private var dataBinding: ActivityMainBinding? = null
    private var mHandler: Messagehandler? = null
    private var mGlideManager: RequestManager? = null
    private var mPagerAdapter: MainPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHandler?.sendEmptyMessage(CoreConfigDef.MainMessageHandlerSplashStart)

        initialize()

    }


    private fun initialize(){
        mGlideManager = Glide.with(this)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mPagerAdapter = MainPagerAdapter(supportFragmentManager,this)

        dataBinding?.viewPager?.adapter = mPagerAdapter
        dataBinding?.tabLayout?.setupWithViewPager(dataBinding?.viewPager)


        for(i in 0..4){
            var tab = dataBinding?.tabLayout?.getTabAt(i)
            tab?.setCustomView(mPagerAdapter?.getTabView(i))
        }
        dataBinding?.viewPager?.setCurrentItem(1)

        if(mHandler == null)
            mHandler = Messagehandler(this)


    }


    private class Messagehandler (reference:MainActivity) : WeakReferenceHandler<MainActivity>(reference){
        override fun handleMessage(mReference: MainActivity?, msg: Message) {
            if(mReference == null) return
            when(msg.what){
                CoreConfigDef.MainMessageHandlerSplashStart -> {
                    //프로그래스 생성

//                    mReference.mAdapter= TongPanListAdapter(mReference.applicationContext,mReference.mitemList,mReference.mGlideManager)
//                    mReference.dataBinding?.basicListView?.layoutManager=LinearLayoutManager(mReference.applicationContext)
//                    mReference.dataBinding?.basicListView?.adapter=mReference.mAdapter
                }
                CoreConfigDef.MainMessageHandlerTongpanLoad -> {
                    //프로그래스 생성
//                    mReference.mAdapter?.notifyDataSetChanged()
                }
                else -> println("not processed")
            }
        }

    }
}
