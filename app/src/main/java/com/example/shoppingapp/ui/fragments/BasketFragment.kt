package com.example.shoppingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
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

        basketAdapter = BasketAdapter(basketViewModel)

        binding.apply {
            basketRecyclerview.adapter = basketAdapter
            basketRecyclerview.layoutManager = LinearLayoutManager(context)

            btnExit.setOnClickListener {
                findNavController().navigate(R.id.action_basketFragment_to_homeFragment)
            }
            basketViewModel.getAllItem.observe(viewLifecycleOwner, {
                basketAdapter.submitList(it)
            })

            btnGoPayment.setOnClickListener {
                //basketViewModel.goPayment(basketViewModel.goPaymentList.value!!)
            }
            basketViewModel.totalPrice.observe(viewLifecycleOwner,{
                basketViewModel.totalPrice(it)
            })

            return binding.root
        }
    }
}