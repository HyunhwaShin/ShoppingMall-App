package com.example.shoppingapp.ui.fragments.menufragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.shoppingapp.adapters.ShopViewPagerAdapter
import com.example.shoppingapp.databinding.FragmentShopBinding
import com.example.shoppingapp.ui.fragments.ShopBookmarkFragment
import com.example.shoppingapp.ui.fragments.ShopRankingFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopFragment: Fragment() {

    val tabLayoutArray = arrayOf("랭킹", "즐겨찾기")

    private lateinit var binding: FragmentShopBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(LayoutInflater.from(context))

        val pagerAdapter = ShopViewPagerAdapter(requireActivity())
        binding.shopViewpager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.shopViewpager){ tab, position ->
            tab.text = tabLayoutArray[position]
        }.attach()

        return binding.root
    }
}