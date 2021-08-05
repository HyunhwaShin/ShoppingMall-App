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
import com.example.shoppingapp.adapters.LikeAdapter
import com.example.shoppingapp.databinding.FragmentLikeBinding
import com.example.shoppingapp.ui.fragments.AlertDialog
import com.example.shoppingapp.viewmodels.LikeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikeFragment : Fragment() {

    private lateinit var binding: FragmentLikeBinding
    lateinit var likeAdapter : LikeAdapter
    private val likeViewModel: LikeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikeBinding.inflate(LayoutInflater.from(context))

        likeAdapter = LikeAdapter(likeViewModel)
        val gridLayoutManager = GridLayoutManager(requireContext(),2)

        binding.apply {
            likeGridRecyclerview.adapter = likeAdapter
            likeGridRecyclerview.layoutManager = gridLayoutManager

            //장바구니 페이지로 이동
            btnBasket.setOnClickListener {
                findNavController().navigate(R.id.action_likeFragment_to_basketFragment)
            }

            //편집 화면으로 변환
            btnEdit.setOnClickListener {
                edit(1)
            }
            //원래 화면으로 복귀
            btnEditCancel.setOnClickListener {
                edit(0)
            }

        }


        //likebutton 누른 찜한 아이템들만 표시
//        likeViewModel.likeStuff.observe(viewLifecycleOwner) {
//            //likeAdapter.submitList(it)
//        }

        //dummy
        likeViewModel.mockStuff.observe(viewLifecycleOwner,{
            likeAdapter.submitList(it)
        })

        likeViewModel.isDelete.observe(viewLifecycleOwner,{
            if (it){
                edit(0)
            }
        })

        return binding.root
    }


    private fun edit(n : Int){
        likeAdapter.updateCheckbox(n)
        likeAdapter.notifyDataSetChanged()

        if(n == 1){ //편집 모드 click 시
            binding.apply {
                btnEdit.visibility = View.GONE
                btnBasket.visibility = View.GONE
                btnDelete.visibility = View.VISIBLE
                btnEditCancel.visibility = View.VISIBLE

                // 삭제
                btnDelete.setOnClickListener {
                    AlertDialog(likeViewModel).show(parentFragmentManager,"dialog")
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