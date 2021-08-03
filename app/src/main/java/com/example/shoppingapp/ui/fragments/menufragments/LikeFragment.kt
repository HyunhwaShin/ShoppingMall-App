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
import com.example.shoppingapp.databinding.FragmentLikeBinding
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.viewmodels.LikeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikeFragment : Fragment() {

    private lateinit var binding: FragmentLikeBinding
    lateinit var stuffAdapter: StuffAdapter
    private val likeViewModel: LikeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikeBinding.inflate(LayoutInflater.from(context))

        stuffAdapter = StuffAdapter()
        val gridLayoutManager = GridLayoutManager(requireContext(),2)

        binding.apply {
            likeGridRecyclerview.adapter = stuffAdapter
            likeGridRecyclerview.layoutManager = gridLayoutManager

            //편집 화면으로 변환
            btnEdit.setOnClickListener {
                Edit(1)
            }
            //원래 화면으로 복귀
            btnEditCancel.setOnClickListener {
                Edit(0)
            }


        }
        //dummy
        likeViewModel.mockStuff.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })

        return binding.root
    }


    private fun Edit(n : Int){
        stuffAdapter.updateCheckbox(n)
        stuffAdapter.notifyDataSetChanged()

        if(n == 1){ //편집 모드 click 시
            binding.apply {
                btnEdit.visibility = View.GONE
                btnBasket.visibility = View.GONE

                btnDelete.visibility = View.VISIBLE
                btnEditCancel.visibility = View.VISIBLE
            }
        }
        else{ //off
            binding.apply {
                btnEdit.visibility = View.VISIBLE
                btnBasket.visibility = View.VISIBLE

                btnDelete.visibility = View.GONE
                btnEditCancel.visibility = View.GONE
            }
        }
    }
}