package com.example.shoppingapp.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ActivityDetailstuffBinding
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.ui.fragments.ColorSizeDialog
import com.example.shoppingapp.viewmodels.DetailStuffViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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
                val bottomSheet = ColorSizeDialog()
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