package com.example.shoppingapp.ui.fragments.menufragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.adapters.StuffAdapter
import com.example.shoppingapp.databinding.FragmentHomeBinding
import com.example.shoppingapp.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var stuffAdapter : StuffAdapter
    private val homeViewModel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(context))

        stuffAdapter = StuffAdapter()

        val gridLayoutManager = GridLayoutManager(requireContext(),2)
        gridLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        binding.apply {
            stuffGridRecyclerview.adapter = stuffAdapter
            stuffGridRecyclerview.layoutManager = gridLayoutManager

            //장바구니 페이지로 이동
            btnBasket.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_basketFragment)
            }
        }

        homeViewModel.getStuff.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })
        return binding.root
    }


}