package com.example.shoppingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.adapters.PaymentAdapter
import com.example.shoppingapp.databinding.FragmentPaymentBinding
import com.example.shoppingapp.viewmodels.PaymentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment: Fragment() {

    val args: PaymentFragmentArgs by navArgs()
    private lateinit var binding: FragmentPaymentBinding
    lateinit var paymentAdapter :PaymentAdapter
    private val paymentViewModel : PaymentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)

        paymentAdapter = PaymentAdapter()

        paymentViewModel.setAllItem(args.basketStuffList.toList())

        paymentViewModel.calculateTotalPrice(args.basketStuffList.toList())

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
                paymentViewModel.setName(editUserName.text.toString())
                paymentViewModel.setPhone(editPhone.text.toString())
                paymentViewModel.setAddress(editAddress.text.toString())
                paymentViewModel.setMemo(editDeliveryMemo.text.toString())
                paymentViewModel.todayDate()

                paymentViewModel.insertCheck()
            }

            btnAccount.setOnClickListener {
                btnAccount.isSelected = btnAccount.isSelected != true
                paymentViewModel.setButton("계좌간편결제")
            }
            btnCard.setOnClickListener {
                btnCard.isSelected = btnCard.isSelected != true
                paymentViewModel.setButton("신용/체크카드")
            }
            btnPhone.setOnClickListener {
                btnPhone.isSelected = btnPhone.isSelected != true
                paymentViewModel.setButton("휴대폰")
            }
            btnNoBankbook.setOnClickListener {
                btnNoBankbook.isSelected = btnNoBankbook.isSelected != true
                paymentViewModel.setButton("무통장")
            }
        }
    }

    private fun setObserver() {

        paymentViewModel.setPrice.observe(viewLifecycleOwner,{
            binding.totalPrice.text = it.toString()
        })

        paymentViewModel.isComplete.observe(viewLifecycleOwner,{
            if (it){
                if(binding.btnCheck1.isChecked && binding.btnCheck2.isChecked) {
                    paymentViewModel.setIsComplete(false)
                    findNavController().navigate(R.id.action_paymentFragment_to_successfulPaymentFragment)
                }else{
                    Toast.makeText(context,"필수사항 동의를 모두 체크해주세요!",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(context,"모든 정보를 입력해주세요!",Toast.LENGTH_LONG).show()
            }
        })

        paymentViewModel.setButton.observe(viewLifecycleOwner,{
            when(it){
                "계좌간편결제" -> {
                    binding.btnCard.isSelected = false
                    binding.btnPhone.isSelected = false
                    binding.btnNoBankbook.isSelected = false
                    if(!binding.btnAccount.isSelected) binding.btnAccount.isSelected = true
                    //if(!binding.btnAccount.isSelected) paymentViewModel.getButton("")
                }
                "신용/체크카드" -> {
                    binding.btnAccount.isSelected = false
                    binding.btnPhone.isSelected = false
                    binding.btnNoBankbook.isSelected = false
                    if(!binding.btnCard.isSelected) binding.btnCard.isSelected = true
                }
                "휴대폰" -> {
                    binding.btnAccount.isSelected = false
                    binding.btnCard.isSelected = false
                    binding.btnNoBankbook.isSelected = false
                    if(!binding.btnPhone.isSelected) binding.btnPhone.isSelected = true
                }
                "무통장"->{
                    binding.btnAccount.isSelected = false
                    binding.btnPhone.isSelected = false
                    binding.btnCard.isSelected = false
                    if(!binding.btnNoBankbook.isSelected) binding.btnNoBankbook.isSelected = true
                }
            }
        })
    }

}