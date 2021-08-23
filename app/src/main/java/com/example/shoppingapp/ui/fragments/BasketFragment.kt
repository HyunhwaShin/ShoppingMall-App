package com.example.shoppingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shoppingapp.adapters.BasketAdapter
import com.example.shoppingapp.databinding.FragmentBasketBinding
import com.example.shoppingapp.viewmodels.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment: Fragment() {
    private lateinit var binding: FragmentBasketBinding
    lateinit var basketAdapter: BasketAdapter
    private val basketViewModel: BasketViewModel by viewModels()

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasketBinding.inflate(LayoutInflater.from(context))


        binding.apply {
            btnExit.setOnClickListener {
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.remove(this@BasketFragment)
                    ?.commit()
                }

            }


        return binding.root
    }
}