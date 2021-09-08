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
        ButtonListener()
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
                paymentViewModel.todayDate()

                paymentViewModel.insertCheck()
            }
            btnAccount.setOnClickListener { ButtonListener() }
            btnCard.setOnClickListener { ButtonListener() }
            btnPhone.setOnClickListener { ButtonListener() }
            btnNoBankbook.setOnClickListener { ButtonListener() }
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
                if(binding.btnCheck1.isChecked || binding.btnCheck2.isChecked) {
                    paymentViewModel.setIsComplete(false)
                    findNavController().navigate(R.id.action_paymentFragment_to_successfulPaymentFragment)
                }else{
                    Toast.makeText(context,"필수사항 동의를 체크해주세요!",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(context,"모든 정보를 입력해주세요!",Toast.LENGTH_LONG).show()
            }
        })
    }

    inner class ButtonListener: View.OnClickListener{
        override fun onClick(view: View?) {
            when(view?.id){
                R.id.btn_account -> paymentViewModel.getButton("계좌간편결제")
                R.id.btn_card -> paymentViewModel.getButton("신용/체크카드")
                R.id.btn_phone -> paymentViewModel.getButton("휴대폰")
                R.id.btn_no_bankbook -> paymentViewModel.getButton("무통장")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        paymentViewModel.makeEmptyPayment(paymentViewModel.getAllItems.value!!)
    }
}