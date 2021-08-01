package com.example.shoppingapp.viewmodels

import androidx.lifecycle.*
import com.example.shoppingapp.db.ShopRanking
import com.example.shoppingapp.repositories.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopRankingViewModel@Inject constructor(
    val shopRepository: ShopRepository,
) : ViewModel() {
    //dummy
    private val _exShopRanking :MutableLiveData<List<ShopRanking>> = MutableLiveData()
    val exShopRanking : LiveData<List<ShopRanking>> = _exShopRanking

    val getAllBookmarkShop : LiveData<List<ShopRanking>> = shopRepository.getAllBookmarkShops().asLiveData()

    init {
        getShopTest()
    }

    fun getShopTest()= viewModelScope.launch{
        val shopList = mutableListOf<ShopRanking>()
        shopList.add(ShopRanking(1,"콩이네 가게",false))
        shopList.add(ShopRanking(2,"숟가락 젓가락",false))
        shopList.add(ShopRanking(3,"멋쟁이 구두",false))
        shopList.add(ShopRanking(4,"수영복 전문점",false))
        _exShopRanking.value = shopList
    }

    fun savedBookmarkShop(shopRanking: ShopRanking) = viewModelScope.launch {
        shopRepository.insertBookmarkShop(shopRanking)
    }
    fun deleteBookmarkShop(shopRanking: ShopRanking) = viewModelScope.launch {
        shopRepository.deleteBookmarkShop(shopRanking)
    }



}