package com.example.shoppingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.adapters.ShopBookmarkAdapter
import com.example.shoppingapp.adapters.ShopRankingAdapter
import com.example.shoppingapp.databinding.FragmentShopbookmarkBinding
import com.example.shoppingapp.viewmodels.ShopBookmarkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopBookmarkFragment: Fragment() {

    private lateinit var binding: FragmentShopbookmarkBinding
    lateinit var shopBookmarkAdapter: ShopBookmarkAdapter
    private val shopBookmarkViewModel: ShopBookmarkViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopbookmarkBinding.inflate(inflater, container, false)

       shopBookmarkAdapter = ShopBookmarkAdapter()

        binding.apply {
            shopBookmarkRecyclerview.adapter = shopBookmarkAdapter
            shopBookmarkRecyclerview.layoutManager = LinearLayoutManager(context)
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
        shopBookmarkViewModel.getAllBookmarkShop.observe(viewLifecycleOwner,{
            shopBookmarkAdapter.submitList(it)
        })
    }
}