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
import com.example.shoppingapp.viewmodels.CategoryViewModel
import com.example.shoppingapp.viewmodels.StuffViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private lateinit var binding : FragmentCategoryBinding
    lateinit var stuffAdapter: StuffAdapter
    private val categoryViewModel : CategoryViewModel by viewModels()

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
                categoryViewModel.getCategoryBest()
            }
            btnClothes.setOnClickListener {
                categoryViewModel.getCategoryClothes()
            }
            btnOnepiece.setOnClickListener {
                categoryViewModel.getCategoryOnepiece()
            }
            btnPants.setOnClickListener {
                categoryViewModel.getCategoryPants()
            }
            btnSkirt.setOnClickListener {
                categoryViewModel.getCategorySkirt()
            }
            btnOuter.setOnClickListener {
                categoryViewModel.getCategoryOuter()
            }
            btnShoes.setOnClickListener {
                categoryViewModel.getCategoryShoes()
            }
            btnAccessories.setOnClickListener {
                categoryViewModel.getCategoryAccessories()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
    }

    fun setObserver(){

        categoryViewModel.categoryBest.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })

        categoryViewModel.categoryClothes.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })

        categoryViewModel.categoryOnepiece.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })

        categoryViewModel.categoryPants.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })

        categoryViewModel.categorySkirt.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })

        categoryViewModel.categoryOuter.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })

        categoryViewModel.categoryShoes.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })

        categoryViewModel.categoryAccessories.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })
    }
}