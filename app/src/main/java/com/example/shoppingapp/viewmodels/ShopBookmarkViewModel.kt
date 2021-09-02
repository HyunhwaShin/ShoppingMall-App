package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingapp.db.ShopRanking
import com.example.shoppingapp.repositories.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShopBookmarkViewModel @Inject constructor(
    val shopRankingRepository: ShopRepository
) : ViewModel(){

    private val _getAllBookmarkShop : MutableLiveData<List<ShopRanking>> = MutableLiveData()
    val getAllBookmarkShop : LiveData<List<ShopRanking>> = _getAllBookmarkShop

    init {
        allBookmarkShop()
    }

    private fun allBookmarkShop() {
        TODO("Not yet implemented")
    }
}