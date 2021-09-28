package com.example.shoppingapp.viewmodels

import androidx.lifecycle.*
import com.example.shoppingapp.db.ShopRanking
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.repositories.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShopRankingViewModel@Inject constructor(
    val shopRepository: ShopRepository
) : ViewModel() {

    private val _getAllShop : MutableLiveData<List<ShopRanking>> = MutableLiveData()
    val getAllShop : LiveData<List<ShopRanking>> = _getAllShop

    private val _bookmarkItem : MutableLiveData<ShopRanking> = MutableLiveData()
    val bookmarkItem : LiveData<ShopRanking>
         get() = _bookmarkItem

    private val _cancelItem : MutableLiveData<ShopRanking> = MutableLiveData()
    val cancelItem : LiveData<ShopRanking>
        get() = _cancelItem

    init {
        getAllShop()
    }

    fun getAllShop() = viewModelScope.launch {
        _getAllShop.value = shopRepository.getShopsAPI()
    }

    fun toggleBookmarkItemList(isCheck: Boolean, shopRanking: ShopRanking){
        if(isCheck){
            _bookmarkItem.value = shopRanking
        }else {
            _cancelItem.value = shopRanking
        }
    }

    fun updateBookmarkItem(shopRanking: ShopRanking)=viewModelScope.launch{
        withContext(Dispatchers.IO){
            shopRepository.insertBookmark(shopRanking)
        }
    }

    fun deleteBookmarkItem(shopRanking: ShopRanking)=viewModelScope.launch{
        withContext(Dispatchers.IO){
            shopRepository.deleteBookmark(shopRanking)
        }
    }



}