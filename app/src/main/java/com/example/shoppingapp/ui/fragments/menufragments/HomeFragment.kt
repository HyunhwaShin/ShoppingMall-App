package com.example.shoppingapp.ui.fragments.menufragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.adapters.StuffAdapter
import com.example.shoppingapp.databinding.FragmentHomeBinding
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var stuffAdapter : StuffAdapter
    private val homeViewModel : HomeViewModel by viewModels()

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
                    homeViewModel.searchTest(etSearch.text.toString())
                    etSearch.setText("")
                    handled = true
                }
                handled
            }

        }

//        homeViewModel.getStuff.observe(viewLifecycleOwner,{
//            stuffAdapter.submitList(it)
//        })
        homeViewModel.mockStuff.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })

        homeViewModel.mockSearch.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })

        return binding.root
    }



}
