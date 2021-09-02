package com.example.shoppingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.adapters.ShopAdapter
import com.example.shoppingapp.databinding.FragmentShoprankingBinding
import com.example.shoppingapp.viewmodels.ShopRankingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopRankingFragment: Fragment() {

    private lateinit var binding: FragmentShoprankingBinding
    lateinit var shopAdapter: ShopAdapter
    private val shopRankingViewModel : ShopRankingViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoprankingBinding.inflate(LayoutInflater.from(context))

        shopAdapter = ShopAdapter()

        binding.apply {
            shopRankingRecyclerview.adapter = shopAdapter
            shopRankingRecyclerview.layoutManager = LinearLayoutManager(context)

        }

        shopRankingViewModel.getAllShop.observe(viewLifecycleOwner,{
            shopAdapter.submitList(it)
        })

        return binding.root
    }
}