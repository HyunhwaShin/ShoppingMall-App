package com.example.shoppingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObserver()
    }

    private fun setListener() {
        binding.apply {
            btnExit.setOnClickListener {
                findNavController().navigate(R.id.action_paymentFragment_to_homeFragment)
            }
            btnPayment.setOnClickListener {
                //저장
                paymentViewModel.getName(editUserName.text.toString())
                paymentViewModel.getPhone(editPhone.text.toString())
                paymentViewModel.getAddress(editAddress.text.toString())
                paymentViewModel.getMemo(editDeliveryMemo.text.toString())

                paymentViewModel.insertCheck()

            }

            //btn 실험 중,,,
            btnNoBankbook.setOnClickListener {
                btnNoBankbook.isSelected = btnNoBankbook.isSelected != true
            }
        }
    }

    private fun setObserver() {
        paymentViewModel.getAllItems.observe(viewLifecycleOwner,{
            paymentAdapter.submitList(it)
        })

        paymentViewModel.getPrice.observe(viewLifecycleOwner,{
            val price = paymentViewModel.calculateTotalPrice(it)
            binding.totalPrice.text = price.toString()
        })

        paymentViewModel.isComplete.observe(viewLifecycleOwner,{
            if (it){
                paymentViewModel.setIsComplete(false)
                findNavController().navigate(R.id.action_paymentFragment_to_successfulPaymentFragment)
            }else{
                Toast.makeText(context,"모든 정보를 입력해주세요!",Toast.LENGTH_LONG).show()
            }
        })
    }




    override fun onStop() {
        super.onStop()
        paymentViewModel.makeEmptyPayment(paymentViewModel.getAllItems.value!!)
    }
}