package com.example.shoppingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoppingapp.databinding.FragmentUsersettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserSettingFragment : Fragment() {
    private lateinit var binding: FragmentUsersettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersettingBinding.inflate(LayoutInflater.from(context))
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}