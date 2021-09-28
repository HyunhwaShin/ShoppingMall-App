package com.example.shoppingapp.ui.activities

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ActivityDetailshopBinding
import com.example.shoppingapp.db.ShopRanking
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailShopActivity: AppCompatActivity() {
    lateinit var binding: ActivityDetailshopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailshop)

        var shop = (intent.getSerializableExtra("shop") as ShopRanking?)!!

        binding.apply {

            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(shop.url)
            }
            shopName.text = shop.shop_name

            btnExit.setOnClickListener {
                finish()
            }
        }
    }
}