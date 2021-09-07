package com.example.shoppingapp.ui.fragments.menufragments

import android.content.SharedPreferences
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
import com.example.shoppingapp.other.Constants.KEY_EMAIL
import com.example.shoppingapp.other.Constants.KEY_NAME
import com.example.shoppingapp.viewmodels.DeliveryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyPageFragment: Fragment() {

    private lateinit var binding: FragmentMypageBinding
    lateinit var deliveryAdapter : DeliveryAdapter
    private val deliveryViewModel : DeliveryViewModel by viewModels()

    @Inject
    lateinit var sharedPreference : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(LayoutInflater.from(context))

        deliveryAdapter = DeliveryAdapter()
        binding.apply {
            mypageRecyclerview.adapter = deliveryAdapter
            mypageRecyclerview.layoutManager = LinearLayoutManager(context)

            //장바구니 페이지로 이동
            btnBasket.setOnClickListener {
                findNavController().navigate(R.id.action_myPageFragment_to_basketFragment)
            }
        }
        loadFieldsFromSharedPref()

//        deliveryViewModel.getAllDeliveries.observe(viewLifecycleOwner,{
//            deliveryAdapter.submitList(it)
//        })

        return binding.root
    }

    private fun loadFieldsFromSharedPref() {
        val name = sharedPreference.getString(KEY_NAME,"")
        val email = sharedPreference.getString(KEY_EMAIL,"")
        binding.userName.text = name
        binding.userEmail.text = email
    }
}