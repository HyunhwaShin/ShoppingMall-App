package com.example.shoppingapp.ui.fragments.menufragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.adapters.ShopViewPagerAdapter
import com.example.shoppingapp.databinding.FragmentShopBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopFragment: Fragment() {

    private lateinit var binding: FragmentShopBinding

    val tabLayoutArray = arrayOf("랭킹", "즐겨찾기")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(inflater, container, false)

        val pagerAdapter = ShopViewPagerAdapter(this)
        binding.apply {
            shopViewpager.adapter = pagerAdapter

            btnBasket.setOnClickListener {
                findNavController().navigate(R.id.action_shopFragment_to_basketFragment)
            }
        }

        TabLayoutMediator(binding.tabLayout, binding.shopViewpager){ tab, position ->
            tab.text = tabLayoutArray[position]
        }.attach()

        return binding.root
    }
}