package com.kindfriends.freefree.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.kindfriends.freefree.CoreConfigDef

import com.kindfriends.freefree.R
import com.kindfriends.freefree.adapter.MainPagerAdapter
import com.kindfriends.freefree.data.ArtistClass
import com.kindfriends.freefree.databinding.ActivityMainBinding
import com.kindfriends.freefree.util.WeakReferenceHandler

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var mdataBinding: ActivityMainBinding? = null
    private var mHandler: Messagehandler? = null
    private var mGlideManager: RequestManager? = null
    private var mPagerAdapter: MainPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        mHandler?.sendEmptyMessage(CoreConfigDef.MainMessageHandlerSplashStart)

        initialize()

    }
    override fun onBackPressed() {
        val drawer = mdataBinding?.drawerLayout
        if(drawer == null)
            return super.onBackPressed()
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.navi_test, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        val id = item.itemId
//
//
//        return if (id == R.id.action_settings) {
//            true
//        } else super.onOptionsItemSelected(item)
//
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        mdataBinding?.drawerLayout?.closeDrawer(GravityCompat.START)
        return true
    }


    private fun initialize(){
        mGlideManager = Glide.with(this)
        mdataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        setSupportActionBar(mdataBinding?.toolbar)
        val toggle = ActionBarDrawerToggle(
                this, mdataBinding?.drawerLayout, mdataBinding?.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)


        mdataBinding?.drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()
        mdataBinding?.navView?.setNavigationItemSelectedListener(this)

        mPagerAdapter = MainPagerAdapter(supportFragmentManager,this)
        mdataBinding?.viewPager?.adapter = mPagerAdapter
        mdataBinding?.tabLayout?.setupWithViewPager(mdataBinding?.viewPager)
        for(i in 0..4){
            var tab = mdataBinding?.tabLayout?.getTabAt(i)
            tab?.setCustomView(mPagerAdapter?.getTabView(i))
        }
//        mdataBinding?.viewPager?.setCurrentItem(1)

        if(mHandler == null)
            mHandler = Messagehandler(this)

        val mArtistInfo = ArtistClass(0,"kimya410",null,"김먀","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","오소마츠 입덕","http://digital-photography-school.com/wp-content/uploads/2012/10/image5.jpg",null,"","")

        mGlideManager?.load(mArtistInfo?.mainImage)
                ?.into(mdataBinding?.artistHome)

        mGlideManager?.load(mArtistInfo?.artistProfile)
                ?.apply(RequestOptions().transform(CircleCrop()))
                ?.into(mdataBinding?.artistImage)

        mdataBinding?.artistName?.setText(mArtistInfo.artistName+"(@${mArtistInfo.artistId})")
        mdataBinding?.comment?.setText(mArtistInfo.comment)

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
