package com.example.shoppingapp.ui.fragments.menufragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.adapters.HomeAdapter
import com.example.shoppingapp.databinding.FragmentCategoryBinding
import com.example.shoppingapp.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private lateinit var binding : FragmentCategoryBinding
    lateinit var homeAdapter: HomeAdapter
    private val homeViewModel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(LayoutInflater.from(context))

        homeAdapter = HomeAdapter()
        val gridLayoutManager = GridLayoutManager(requireContext(),3)

        binding.apply {
            categoryGridRecyclerview.adapter = homeAdapter
            categoryGridRecyclerview.layoutManager = gridLayoutManager

            //장바구니
            btnBasket.setOnClickListener {
                findNavController().navigate(R.id.action_categoryFragment_to_basketFragment)
            }
        }
        homeViewModel.mockStuff.observe(viewLifecycleOwner,{
            homeAdapter.submitList(it)
        })


        return binding.root
    }
}