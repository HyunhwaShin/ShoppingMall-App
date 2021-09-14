package com.example.shoppingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.shoppingapp.R
import com.example.shoppingapp.adapters.SelectDetailAdapter
import com.example.shoppingapp.databinding.FragmentSelectdetaildialogBinding
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.viewmodels.SelectedDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class SelectDetailDialog : BottomSheetDialogFragment(){
    private lateinit var binding : FragmentSelectdetaildialogBinding
    lateinit var selectDetailAdapter: SelectDetailAdapter
    private val selectedDetailViewModel: SelectedDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectdetaildialogBinding.inflate(LayoutInflater.from(context))
        isCancelable = false
        selectDetailAdapter = SelectDetailAdapter()

        setSpinner()
        setObserver()

        var basket = requireActivity().intent!!.getSerializableExtra("stuff") as Stuff?

        val color = resources.getStringArray(R.array.color_spinner)
        val size = resources.getStringArray(R.array.size_spinner)
        val colorSpinnerAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, color)
        val sizeSpinnerAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, size)

        binding.apply {
            colorSpinner.adapter = colorSpinnerAdapter
            sizeSpinner.adapter = sizeSpinnerAdapter

            btnExit.setOnClickListener {
                dismiss()
            }

            btnGoBasket.setOnClickListener {
                basket?.product_name?.let { it -> selectedDetailViewModel.getSelectedStuffName(it) }
                basket?.product_price?.let { it -> selectedDetailViewModel.getSelectedPrice(it) }
                basket?.product_img?.let { it -> selectedDetailViewModel.getSelectedImage(it) }
                basket?.shop_name?.let { it -> selectedDetailViewModel.getSelectedShopName(it) }

                selectedDetailViewModel.check()
            }

        }
        return binding.root
    }

    private fun setSpinner(){
        binding.colorSpinner.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedColor = binding.colorSpinner.selectedItem.toString()
                selectedDetailViewModel.getSelectedColor(selectedColor)
                binding.tvColor.text = selectedColor

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.sizeSpinner.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedSize= binding.sizeSpinner.selectedItem.toString()
                selectedDetailViewModel.getSelectedSize(selectedSize)
                binding.tvSize.text = selectedSize

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
    private fun setObserver(){
        selectedDetailViewModel.isComplete.observe(viewLifecycleOwner,{
            if(it){
                Toast.makeText(context,"장바구니에 담겼습니다.", Toast.LENGTH_LONG).show()
                runBlocking { delay(1000) }
                dismiss()
            }else{
                Toast.makeText(context,"색상과 사이즈를 모두 선택해주세요.", Toast.LENGTH_LONG).show()
            }
        })


    }
}