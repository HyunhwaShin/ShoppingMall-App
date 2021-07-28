package com.example.shoppingapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentUsersettingBinding
import com.example.shoppingapp.other.Constants.KEY_NAME
import com.example.shoppingapp.other.Constants.KEY_EMAIL
import com.example.shoppingapp.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserSettingFragment : Fragment() {
    private lateinit var binding: FragmentUsersettingBinding

    @set:Inject
    var isFirstAppOpen = true

    @Inject
    lateinit var sharedPref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersettingBinding.inflate(LayoutInflater.from(context))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isFirstAppOpen) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.userSettingFragment, true)
                .build()
            findNavController().navigate(
                R.id.action_userSettingFragment_to_homeFragment,
                savedInstanceState,
                navOptions
            )
        }
        binding.btnStart.setOnClickListener {
            val success = writePersonalDataToSharedPref()
            if (success) {
                findNavController().navigate(R.id.action_userSettingFragment_to_homeFragment)
            } else {
                Snackbar.make(requireView(), "이름과 이메일을 모두 입력해주세요", Snackbar.LENGTH_SHORT).show()

            }
        }

    }
    private fun writePersonalDataToSharedPref(): Boolean {

        val name = binding.etUserName.text.toString()
        val email = binding.etEmail.text.toString()

        if (name.isEmpty() || email.isEmpty()) {
            return false
        }
        sharedPref.edit()
            .putString(KEY_NAME, name)
            .putString(KEY_EMAIL, email)
            .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
            .apply()
        //name, email 적용해서 넣을 공간
        return true
    }
}
