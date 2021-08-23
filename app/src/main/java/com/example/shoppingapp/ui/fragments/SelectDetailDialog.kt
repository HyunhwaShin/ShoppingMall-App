package com.example.shoppingapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.shoppingapp.R
import com.example.shoppingapp.adapters.SelectDetailAdapter
import com.example.shoppingapp.databinding.FragmentSelectdetaildialogBinding
import com.example.shoppingapp.viewmodels.SelectedDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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

        selectDetailAdapter = SelectDetailAdapter(selectedDetailViewModel)
        setSpinner()

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
                //repository.insert 이런걸로 모든정보 저장!
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
                selectedDetailViewModel._getColor.value = selectedColor


//                selectedDetailViewModel.getColor.observe(viewLifecycleOwner,{
//                    selectDetailAdapter.submitList(selectedColor)
//                })
               // sharedPref.edit().putString(KEY_COLOR, selectedColor).apply()
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
                selectedDetailViewModel._getSize.value = selectedSize


//                selectedDetailViewModel.getSize.observe(viewLifecycleOwner,{
//                    selectDetailAdapter.submitList(selectedSize)
//                })
               // sharedPref.edit().putString(KEY_SIZE, selectedSize).apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
//    private fun loadDataFromSharedPref() {
//        val loadColor = sharedPref.getString(Constants.KEY_COLOR,"")
//        val loadSize = sharedPref.getString(Constants.KEY_SIZE,"")
//        binding.color.text= loadColor
//        binding.size.text= loadSize
//    }
}