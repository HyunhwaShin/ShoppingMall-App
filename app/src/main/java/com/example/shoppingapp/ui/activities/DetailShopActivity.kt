package com.example.shoppingapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ActivityDetailshopBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailShopActivity: AppCompatActivity() {
    lateinit var binding: ActivityDetailshopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailshop)
    }
}