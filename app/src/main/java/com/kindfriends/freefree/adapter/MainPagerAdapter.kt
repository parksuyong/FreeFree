package com.kindfriends.freefree.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import com.kindfriends.freefree.R
import com.kindfriends.freefree.databinding.CustomMainTabBinding
import com.kindfriends.freefree.view.MainAlarmFragment
import com.kindfriends.freefree.view.MainHomeFragment
import com.kindfriends.freefree.view.MainMenuFragment
import com.kindfriends.freefree.view.MainSearchFragment

/**
 * Created by qkrtndyd1234 on 2017. 10. 28..
 */

class MainPagerAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {
    internal val PAGE_COUNT = 4

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment {

        when (position) {
            0 -> {
                return MainHomeFragment.newInstance("","")
            }
            1 -> {
                return MainSearchFragment.newInstance("","")
            }
            2 -> {
                return MainAlarmFragment.newInstance("","")
            }
            3 -> {
                return MainMenuFragment.newInstance("","")
            }
        }

        return MainHomeFragment.newInstance("","")

    }

    override fun getPageTitle(position: Int): CharSequence? {
        // Generate title based on item position
        return null
    }

    fun getTabView(position: Int): View {
        var tabView = LayoutInflater.from(context).inflate(R.layout.custom_main_tab,null)
        var dataBinding: CustomMainTabBinding = DataBindingUtil.bind(tabView)
        when (position) {
            0 -> {
                dataBinding.tabIcon.setImageResource(R.drawable.main_tab_home)
            }
            1 -> {
                dataBinding.tabIcon.setImageResource(R.drawable.main_tab_search)
            }
            2 -> {
                dataBinding.tabIcon.setImageResource(R.drawable.main_tab_alarm)
            }
            3 -> {
                dataBinding.tabIcon.setImageResource(R.drawable.main_tab_menu)
            }

        }

        return dataBinding.root;
    }
}
