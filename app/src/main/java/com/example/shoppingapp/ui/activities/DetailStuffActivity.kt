package com.example.shoppingapp.ui.activities

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ActivityDetailstuffBinding
import com.example.shoppingapp.db.Stuff
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailStuffActivity : AppCompatActivity(){
    lateinit var binding: ActivityDetailstuffBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailstuff)

        var stuff = intent.getSerializableExtra("stuff") as Stuff

        binding.apply {

            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(stuff.product_link)
            }
            shopName.text = stuff.shop_name
            price.text = stuff.product_price.toString()

            btnExit.setOnClickListener{
                finish()
            }
        }
    }
}