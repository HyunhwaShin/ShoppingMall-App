package com.example.shoppingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shoppingapp.adapters.ShopAdapter
import com.example.shoppingapp.databinding.FragmentShopbookmarkBinding
import com.example.shoppingapp.viewmodels.ShopRankingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopBookmarkFragment: Fragment() {

    private lateinit var binding: FragmentShopbookmarkBinding
    private lateinit var shopAdapter: ShopAdapter
    private val shopRankingViewModel : ShopRankingViewModel by viewModels()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopbookmarkBinding.inflate(LayoutInflater.from(context))



        return binding.root
    }
}