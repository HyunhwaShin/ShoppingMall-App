package com.example.shoppingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoppingapp.databinding.FragmentShopbookmarkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopBookmarkFragment: Fragment() {

    private lateinit var binding: FragmentShopbookmarkBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopbookmarkBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }
}