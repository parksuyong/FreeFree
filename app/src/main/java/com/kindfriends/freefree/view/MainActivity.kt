package com.kindfriends.freefree.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.kindfriends.freefree.CoreConfigDef

import com.kindfriends.freefree.R
import com.kindfriends.freefree.adapter.TongPanListAdapter
import com.kindfriends.freefree.data.TongPanListItem
import com.kindfriends.freefree.databinding.ActivityMainBinding
import com.kindfriends.freefree.util.WeakReferenceHandler
import java.util.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private var dataBinding: ActivityMainBinding? = null
    private var mHandler: Messagehandler? = null
    private var mitemList = ArrayList<TongPanListItem>()
    private var mAdapter: TongPanListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHandler?.sendEmptyMessage(CoreConfigDef.MainMessageHandlerSplashStart)

        initialize()


    }


    private fun initialize(){
        bindInit()
        getItems()

        mHandler?.sendEmptyMessage(CoreConfigDef.MainMessageHandlerSplashStart)

    }
    private fun bindInit() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }


    private fun getItems(){
        mitemList.add(TongPanListItem(1,"pig","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책",CoreConfigDef.TongPanListBasicView))
        mitemList.add(TongPanListItem(2,"Penguin","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책",CoreConfigDef.TongPanListBasicView))
        mitemList.add(TongPanListItem(3,"Eagle","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책",CoreConfigDef.TongPanListBasicView))
        mitemList.add(TongPanListItem(4,"Rabbit","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책",CoreConfigDef.TongPanListBasicView))
        mitemList.add(TongPanListItem(5,"Dolphin","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책",CoreConfigDef.TongPanListBasicView))
        mitemList.add(TongPanListItem(6,"Snek","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책",CoreConfigDef.TongPanListBasicView))
        mitemList.add(TongPanListItem(7,"Seal","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책",CoreConfigDef.TongPanListBasicView))
        mitemList.add(TongPanListItem(8,"Rhino","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책",CoreConfigDef.TongPanListBasicView))
        mitemList.add(TongPanListItem(9,"Leopard","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책",CoreConfigDef.TongPanListBasicView))
        mitemList.add(TongPanListItem(10,"Hippo","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책",CoreConfigDef.TongPanListBasicView))

    }


    override fun onClick(view: View?) {
        if(view == null) return
        when(view.id){
            R.id.text -> {

            }
        }
    }

    class Messagehandler (reference:MainActivity) : WeakReferenceHandler<MainActivity>(reference){
        override fun handleMessage(mReference: MainActivity?, msg: Message) {
            if(mReference == null) return
            when(msg.what){
                CoreConfigDef.MainMessageHandlerSplashStart -> {
                    //프로그래스 생성

                    mReference.mAdapter= TongPanListAdapter(mReference.applicationContext,mReference.mitemList)
                    mReference.dataBinding?.basicListView?.layoutManager=LinearLayoutManager(mReference.applicationContext)
                    mReference.dataBinding?.basicListView?.adapter=mReference.mAdapter
                }
                CoreConfigDef.MainMessageHandlerTongpanLoad -> {
                    //프로그래스 생성
                    mReference.mAdapter?.notifyDataSetChanged()
                }
                else -> println("not processed")
            }
        }

    }
}
