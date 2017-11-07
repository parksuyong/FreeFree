package com.kindfriends.freefree.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.kindfriends.freefree.CoreConfigDef

import com.kindfriends.freefree.R
import com.kindfriends.freefree.adapter.ArtistTongpanAdapter
import com.kindfriends.freefree.data.ArtistClass
import com.kindfriends.freefree.data.TongPanClass
import com.kindfriends.freefree.databinding.ActivityArtistBinding
import com.kindfriends.freefree.util.WeakReferenceHandler

class ArtistActivity : AppCompatActivity() {

    private var mdataBinding: ActivityArtistBinding? = null
    private var mGlideManager: RequestManager? = null
    private var mTongpanList: ArrayList<TongPanClass>?=null
    private var mArtistInfo: ArtistClass? = null
    private var mHandler: Messagehandler? = null
    private var mAdapter: ArtistTongpanAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

    }

    private fun initialize(){
        mGlideManager = Glide.with(this)
        if(mHandler == null)
            mHandler = Messagehandler(this)
        mdataBinding = DataBindingUtil.setContentView(this,R.layout.activity_artist)

        mTongpanList = ArrayList<TongPanClass>()

        mArtistInfo = ArtistClass(0,"kimya410",null,"김먀","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","오소마츠 입덕","http://digital-photography-school.com/wp-content/uploads/2012/10/image5.jpg",null,"","")
        for(i in 0..10){
            val tongpanInfo=TongPanClass(mArtistInfo,i,"8월 오소마츠 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes","http://digital-photography-school.com/wp-content/uploads/2012/10/image5.jpg",
                    "오소마츠상;스티커;컵;아크릴스탠드;책",-1,"10/29","11/10","","",null,null)
            mTongpanList?.add(tongpanInfo)
        }
        val extras = intent.extras
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = extras.getString("image_transition_name")
            mdataBinding?.artistImage?.transitionName=imageTransitionName
        }
        var glideOptions = RequestOptions()
        glideOptions.dontAnimate()

        mGlideManager?.load(mArtistInfo?.artistProfile)
                ?.apply(glideOptions)
                ?.listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            startPostponedEnterTransition()
                        }
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            startPostponedEnterTransition()
                        }
                        return false
                    }
                })
                ?.into(mdataBinding?.artistImage)


        mGlideManager?.load(mArtistInfo?.mainImage)
                ?.into(mdataBinding?.headImage)
        mdataBinding?.artistName?.setText(mArtistInfo?.artistName)
        mdataBinding?.comment?.setText(mArtistInfo?.comment)

        mHandler?.sendEmptyMessage(CoreConfigDef.ArtistHandlerFirstLoadItems)


    }

    fun onClickArtistItem(tongPanItem: TongPanClass,onImageView: ImageView){

        val intent = Intent(applicationContext, TongPanActivity::class.java)
        intent.putExtra("tongpan", tongPanItem)
        intent.putExtra("image_transition_name", ViewCompat.getTransitionName(onImageView))

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                onImageView,
                ViewCompat.getTransitionName(onImageView))
        startActivityForResult(intent,CoreConfigDef.ArtistIntentResultCodeTongPanActivity ,options.toBundle())


    }


    private class Messagehandler (reference:ArtistActivity) : WeakReferenceHandler<ArtistActivity>(reference){
        override fun handleMessage(mReference: ArtistActivity?, msg: Message) {
            if(mReference == null) return
            when(msg.what){
                CoreConfigDef.ArtistHandlerFirstLoadItems -> {
                    //프로그래스 생성
                    Log.i("tag","artist"+mReference.mTongpanList?.size)
                    mReference.mAdapter= ArtistTongpanAdapter(mReference,mReference.mTongpanList,mReference.mGlideManager)
                    mReference.mdataBinding?.tongpanListView?.layoutManager = LinearLayoutManager(mReference.applicationContext)
                    mReference.mdataBinding?.tongpanListView?.adapter=mReference.mAdapter
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
