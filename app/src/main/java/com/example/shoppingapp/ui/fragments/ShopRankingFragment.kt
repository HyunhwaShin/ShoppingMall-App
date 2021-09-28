package com.example.shoppingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.adapters.ShopRankingAdapter
import com.example.shoppingapp.databinding.FragmentShoprankingBinding
import com.example.shoppingapp.viewmodels.ShopRankingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopRankingFragment: Fragment() {

    private lateinit var binding: FragmentShoprankingBinding
    lateinit var shopRankingAdapter: ShopRankingAdapter
    private val shopRankingViewModel : ShopRankingViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoprankingBinding.inflate(inflater, container, false)

        shopRankingAdapter = ShopRankingAdapter(shopRankingViewModel)

        binding.apply {
            shopRankingRecyclerview.adapter = shopRankingAdapter
            shopRankingRecyclerview.layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObserver()
    }
    private fun setListener(){

    }
    private fun setObserver(){

        shopRankingViewModel.getAllShop.observe(viewLifecycleOwner,{
            shopRankingAdapter.submitList(it)
        })

        shopRankingViewModel.bookmarkItem.observe(viewLifecycleOwner,{
            shopRankingViewModel.updateBookmarkItem(it)
        })

        shopRankingViewModel.cancelItem.observe(viewLifecycleOwner,{
            shopRankingViewModel.deleteBookmarkItem(it)
        })
    }
}