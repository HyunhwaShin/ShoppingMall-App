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
import com.example.shoppingapp.adapters.PaymentAdapter
import com.example.shoppingapp.databinding.FragmentPaymentBinding
import com.example.shoppingapp.viewmodels.PaymentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment: Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    lateinit var paymentAdapter :PaymentAdapter
    private val paymentViewModel : PaymentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(LayoutInflater.from(context))

        paymentAdapter = PaymentAdapter()

        binding.apply {
            paymentRecyclerview.adapter = paymentAdapter
            paymentRecyclerview.layoutManager = LinearLayoutManager(context)

            btnExit.setOnClickListener {

                findNavController().navigate(R.id.action_paymentFragment_to_homeFragment)
            }
        }
        paymentViewModel.getAllItems.observe(viewLifecycleOwner,{
            paymentAdapter.submitList(it)
        })
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        paymentViewModel.makeEmptyPayment(paymentViewModel.getAllItems.value!!)
    }
}