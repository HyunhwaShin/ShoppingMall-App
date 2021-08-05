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
import com.example.shoppingapp.ui.fragments.AlertDialog
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

            //장바구니 페이지로 이동
            btnBasket.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_basketFragment)
            }

            //편집 화면으로 변환
            btnEdit.setOnClickListener {
                Edit(1)
            }
            //원래 화면으로 복귀
            btnEditCancel.setOnClickListener {
                Edit(0)
            }

        }
        var id = requireActivity().intent.getLongExtra("id",0L)
        likeViewModel.getLikeStuff(id)

        //likebutton 누른 찜한 아이템들만 표시
        likeViewModel.likeStuff.observe(viewLifecycleOwner) {
            //stuffAdapter.submitList(it)
        }

        //dummy
        likeViewModel.mockStuff.observe(viewLifecycleOwner,{
            stuffAdapter.submitList(it)
        })

        likeViewModel.isDelete.observe(viewLifecycleOwner,{
            if (it){
                // == finish()
                activity?.supportFragmentManager
                        ?.beginTransaction()
                        ?.remove(this)
                        ?.commit()
            }
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

                // 삭제
                btnDelete.setOnClickListener {
                    AlertDialog().show(parentFragmentManager,"dialog")
                }

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