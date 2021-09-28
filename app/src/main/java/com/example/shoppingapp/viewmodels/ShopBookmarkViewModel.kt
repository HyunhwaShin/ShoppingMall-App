package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.ShopRanking
import com.example.shoppingapp.repositories.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopBookmarkViewModel @Inject constructor(
    val shopRankingRepository: ShopRepository
) : ViewModel(){

    private val _getAllBookmarkShop : MutableLiveData<List<ShopRanking>> = MutableLiveData()
    val getAllBookmarkShop : LiveData<List<ShopRanking>>
        get() = _getAllBookmarkShop

    init {
        allBookmarkShop()
    }

    private fun allBookmarkShop() = viewModelScope.launch {
        shopRankingRepository.getAllBookmarkShops().collect {
            _getAllBookmarkShop.value = it
        }
    }
}