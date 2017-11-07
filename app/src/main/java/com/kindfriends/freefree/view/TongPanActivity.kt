package com.kindfriends.freefree.view

import android.databinding.DataBindingUtil
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
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
import com.kindfriends.freefree.databinding.ActivityTongPanBinding
import com.kindfriends.freefree.databinding.FragmentTongBinding
import com.kindfriends.freefree.util.WeakReferenceHandler

class TongPanActivity : AppCompatActivity() {
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var mDataBingding: ActivityTongPanBinding?=null
    private var mGlideManager: RequestManager? = null
    private var mHandler: Messagehandler? = null
    private var mTongpan:TongPanClass?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

    }

    private fun initialize(){
        mDataBingding = DataBindingUtil.setContentView(this,R.layout.activity_tong_pan)



        mGlideManager = Glide.with(this)
        if(mHandler == null)
            mHandler = Messagehandler(this)


        val extras = intent.extras
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = extras.getString("image_transition_name")
            mDataBingding?.tongpanImage?.transitionName=imageTransitionName
        }
        mTongpan = extras?.getParcelable<TongPanClass>("tongpan")
        var glideOptions = RequestOptions()
        glideOptions.dontAnimate()

        mGlideManager?.load(mTongpan?.mainImage)
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
                ?.into(mDataBingding?.tongpanImage)






        mHandler?.sendEmptyMessage(CoreConfigDef.TongPanMainHandlerInitialize)

        val toolbar = mDataBingding?.toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        toolbar?.title="8월 통판 판매"
        toolbar?.setNavigationIcon(R.drawable.abc_ic_ab_back_material)

        setSupportActionBar(toolbar)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
//        mViewPager = findViewById<View>(R.id.container) as ViewPager
//        mViewPager!!.adapter = mSectionsPagerAdapter
        mDataBingding?.container?.adapter = mSectionsPagerAdapter

        mDataBingding?.tabs?.setupWithViewPager(mDataBingding?.container)
//        val tabLayout = findViewById(R.id.tabs) as TabLayout
//        tabLayout.setupWithViewPager(mViewPager)

        mDataBingding!!.fab.setOnClickListener(View.OnClickListener {
            view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        })

        mDataBingding?.progressLayout?.visibility=View.VISIBLE



    }
    private fun initDisplay(){

    }
    private fun getItems(){

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_tong_pan, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        }else if( id == android.R.id.home){
            onBackPressed()
            true
        }else super.onOptionsItemSelected(item)

    }


    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 5
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return "SECTION $position"

//            when (position) {
//                0 -> return "SECTION 1"
//                1 -> return "SECTION 2"
//                2 -> return "SECTION 3"
//                3 -> return "SECTION 3"
//                4 -> return "SECTION 3"
//                5 -> return "SECTION 3"
//            }
//            return null
        }
    }

    inner class Messagehandler (reference:TongPanActivity) : WeakReferenceHandler<TongPanActivity>(reference){
        override fun handleMessage(mReference: TongPanActivity?, msg: Message) {
            if(mReference == null) return
            when(msg.what){
                CoreConfigDef.TongPanMainHandlerInitialize -> {
                    //프로그래스 생성
                    mReference?.mDataBingding?.progressLayout?.visibility=View.VISIBLE

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
    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {
        private var mDataBing: FragmentTongBinding?=null

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            if(mDataBing != null)
                return mDataBing?.root

            mDataBing = DataBindingUtil.inflate(inflater,R.layout.fragment_tong,container,false)
            mDataBing?.sectionLabel?.setText(getString(R.string.large_text))

            return mDataBing?.root
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
