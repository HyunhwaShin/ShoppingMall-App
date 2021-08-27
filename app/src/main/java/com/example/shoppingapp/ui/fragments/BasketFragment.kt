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

            return binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObserver()
    }

    private fun setListener() {
        binding.apply {
            btnExit.setOnClickListener {
                findNavController().navigate(R.id.action_basketFragment_to_homeFragment)
            }
            btnGoPayment.setOnClickListener {
                basketViewModel.goPaymentList(basketViewModel.basketToPaymentList.value!!)
                findNavController().navigate(R.id.action_basketFragment_to_paymentFragment)
            }
        }
    }

    private fun setObserver() {
        basketViewModel.getAllItem.observe(viewLifecycleOwner, {
            basketAdapter.submitList(it)
        })

        basketViewModel.totalPrice.observe(viewLifecycleOwner,{
            binding.totalPrice.text = it.toString()
        })
    }
}