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
import com.example.shoppingapp.adapters.StuffAdapter
import com.example.shoppingapp.databinding.FragmentCategoryBinding
import com.example.shoppingapp.viewmodels.StuffViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private lateinit var binding : FragmentCategoryBinding
    lateinit var stuffAdapter: StuffAdapter
    private val stuffViewModel : StuffViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(LayoutInflater.from(context))

        stuffAdapter = StuffAdapter()
        val gridLayoutManager = GridLayoutManager(requireContext(),3)

        binding.apply {
            categoryGridRecyclerview.adapter = stuffAdapter
            categoryGridRecyclerview.layoutManager = gridLayoutManager

            //장바구니
            btnBasket.setOnClickListener {
                findNavController().navigate(R.id.action_categoryFragment_to_basketFragment)
            }

            btnBest.setOnClickListener {
                stuffViewModel.categoryBest.observe(viewLifecycleOwner,{
                    stuffAdapter.submitList(it)
                })
            }
            btnClothes.setOnClickListener {
                stuffViewModel.categoryClothes.observe(viewLifecycleOwner,{
                    stuffAdapter.submitList(it)
                })
            }
            btnOnepiece.setOnClickListener {
                stuffViewModel.categoryOnepiece.observe(viewLifecycleOwner,{
                    stuffAdapter.submitList(it)
                })
            }
            btnPants.setOnClickListener {
                stuffViewModel.categoryPants.observe(viewLifecycleOwner,{
                    stuffAdapter.submitList(it)
                })
            }
            btnSkirt.setOnClickListener {
                stuffViewModel.categorySkirt.observe(viewLifecycleOwner,{
                    stuffAdapter.submitList(it)
                })
            }
            btnOuter.setOnClickListener {
                stuffViewModel.categoryOuter.observe(viewLifecycleOwner,{
                    stuffAdapter.submitList(it)
                })
            }
            btnShoes.setOnClickListener {
                stuffViewModel.categoryShoes.observe(viewLifecycleOwner,{
                    stuffAdapter.submitList(it)
                })
            }
            btnAccessories.setOnClickListener {
                stuffViewModel.categoryAccessories.observe(viewLifecycleOwner,{
                    stuffAdapter.submitList(it)
                })
            }
        }


        stuffViewModel.mockStuff.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })


        return binding.root
    }
}