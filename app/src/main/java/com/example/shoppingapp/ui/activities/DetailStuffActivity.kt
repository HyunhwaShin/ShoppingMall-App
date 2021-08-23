package com.example.shoppingapp.ui.activities

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ActivityDetailstuffBinding
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.ui.fragments.SelectDetailDialog
import com.example.shoppingapp.viewmodels.DetailStuffViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailStuffActivity : FragmentActivity(){
    lateinit var binding: ActivityDetailstuffBinding
    private val detailStuffViewModel : DetailStuffViewModel by viewModels()

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
            btnFavorite.setOnCheckedChangeListener { btn, isCheck ->
                detailStuffViewModel.toggleLikeItem(isCheck,stuff)
            }
            btnSelectDetail.setOnClickListener {
                val bottomSheet = SelectDetailDialog()
                bottomSheet.show(supportFragmentManager,bottomSheet.tag)
            }
        }
        detailStuffViewModel.likeItem.observe(this,{
            detailStuffViewModel.likeItem(stuff)
        })
        detailStuffViewModel.cancelItem.observe(this,{
            detailStuffViewModel.cancelLikeItem(stuff)
        })
    }

}