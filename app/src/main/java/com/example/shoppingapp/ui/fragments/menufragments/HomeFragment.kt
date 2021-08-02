package com.example.shoppingapp.ui.fragments.menufragments

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.adapters.StuffAdapter
import com.example.shoppingapp.databinding.FragmentHomeBinding
import com.example.shoppingapp.viewmodels.StuffViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var stuffAdapter : StuffAdapter
    private val stuffViewModel : StuffViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        stuffAdapter = StuffAdapter()

        val gridLayoutManager = GridLayoutManager(requireContext(),2)

        binding.apply {
            stuffGridRecyclerview.adapter = stuffAdapter
            stuffGridRecyclerview.layoutManager = gridLayoutManager

            //장바구니 페이지로 이동
            btnBasket.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_basketFragment)
            }

            //검색
            etSearch.setOnEditorActionListener { v, actionId, event ->
                var handled = false
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //search event with server
                    stuffViewModel.searchTest(etSearch.text.toString())
                    etSearch.setText("")
                    handled = true
                }
                handled
            }

        }

//        homeViewModel.getStuff.observe(viewLifecycleOwner,{
//            stuffAdapter.submitList(it)
//        })
        //dummy
        stuffViewModel.mockStuff.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })

        stuffViewModel.mockSearch.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })

        return binding.root
    }



}
