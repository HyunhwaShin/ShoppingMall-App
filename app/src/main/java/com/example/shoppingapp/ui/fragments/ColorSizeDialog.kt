package com.example.shoppingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentColorsizedialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class ColorSizeDialog : BottomSheetDialogFragment(){
    private lateinit var binding : FragmentColorsizedialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentColorsizedialogBinding.inflate(LayoutInflater.from(context))
        isCancelable = false

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

        }



        return binding.root
    }


}