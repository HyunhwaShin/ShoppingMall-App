package com.example.shoppingapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shoppingapp.other.Constants.PAGE_COUNT
import com.example.shoppingapp.ui.fragments.ShopBookmarkFragment
import com.example.shoppingapp.ui.fragments.ShopRankingFragment

class ShopViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    var fragments : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int {
        return PAGE_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> ShopRankingFragment()
            else -> ShopBookmarkFragment()
        }
    }
}