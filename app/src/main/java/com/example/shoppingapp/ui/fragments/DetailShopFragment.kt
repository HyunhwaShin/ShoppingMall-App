package com.example.shoppingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoppingapp.databinding.FragmentDetailshopBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailShopFragment: Fragment() {
    private lateinit var binding: FragmentDetailshopBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailshopBinding.inflate(LayoutInflater.from(context))
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}