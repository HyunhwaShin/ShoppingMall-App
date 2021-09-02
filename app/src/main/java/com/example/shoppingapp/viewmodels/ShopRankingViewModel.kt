package com.example.shoppingapp.viewmodels

import androidx.lifecycle.*
import com.example.shoppingapp.db.ShopRanking
import com.example.shoppingapp.repositories.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShopRankingViewModel@Inject constructor(
    val shopRepository: ShopRepository,
) : ViewModel() {

    private val _getAllShop : MutableLiveData<List<ShopRanking>> = MutableLiveData()
    val getAllShop : LiveData<List<ShopRanking>> = _getAllShop

    init {
        getAllShop()
    }

    fun getAllShop() = viewModelScope.launch {
        _getAllShop.value = shopRepository.getShopsAPI()
    }

//    fun savedBookmarkShop(shopRanking: ShopRanking) = viewModelScope.launch {
//        shopRepository.insertBookmarkShop(shopRanking)
//    }
//    fun deleteBookmarkShop(shopRanking: ShopRanking) = viewModelScope.launch {
//        shopRepository.deleteBookmarkShop(shopRanking)
//    }

}