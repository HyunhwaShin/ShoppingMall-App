package com.example.shoppingapp.ui.fragments.menufragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoppingapp.databinding.FragmentMypageBinding
import com.example.shoppingapp.other.Constants.KEY_EMAIL
import com.example.shoppingapp.other.Constants.KEY_NAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyPageFragment: Fragment() {

    private lateinit var binding: FragmentMypageBinding

    @Inject
    lateinit var sharedPreference : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(LayoutInflater.from(context))

        loadFieldsFromSharedPref()

        return binding.root
    }

    private fun loadFieldsFromSharedPref() {
        val name = sharedPreference.getString(KEY_NAME,"")
        val email = sharedPreference.getString(KEY_EMAIL,"")
        binding.userName.text = name
        binding.userEmail.text = email
    }
}