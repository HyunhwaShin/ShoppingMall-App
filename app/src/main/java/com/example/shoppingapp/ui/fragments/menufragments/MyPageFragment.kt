package com.example.shoppingapp.ui.fragments.menufragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.adapters.DeliveryAdapter
import com.example.shoppingapp.databinding.FragmentMypageBinding
import com.example.shoppingapp.db.Delivery
import com.example.shoppingapp.viewmodels.DeliveryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment: Fragment() {

    private lateinit var binding: FragmentMypageBinding
    lateinit var deliveryAdapter : DeliveryAdapter
    private val deliveryViewModel : DeliveryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)

        deliveryAdapter = DeliveryAdapter()
        binding.apply {
            mypageRecyclerview.adapter = deliveryAdapter
            mypageRecyclerview.layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObserver()
    }

    private fun setListener(){
        binding.apply {
            btnBasket.setOnClickListener {
                findNavController().navigate(R.id.action_myPageFragment_to_basketFragment)
            }
        }
    }

    private fun setObserver(){
        deliveryViewModel.getAllDeliveries.observe(viewLifecycleOwner,{
            val deliveryList = mutableListOf<Delivery>()
            for(delivery in deliveryList){
                    deliveryList.add(Delivery(
                        delivery.orderNumber,
                        delivery.orderDate,
                        delivery.userEmail,
                        delivery.name,
                        delivery.phoneNumber,
                        delivery.addr,
                        delivery.memo,
                        delivery.payType,
                        delivery.price,
                        delivery.orderInfo,
                        delivery.isPayment,
                        delivery.shopName,
                        delivery.img
                    ))
                }
            deliveryAdapter.submitList(deliveryList)
        })
        deliveryViewModel.user.observe(viewLifecycleOwner,{
            binding.userEmail.text  = it.email
            binding.userName.text = it.name
        })
    }
}