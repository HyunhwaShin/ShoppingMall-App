package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.repositories.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    val basketRepository: BasketRepository
): ViewModel() {
    private val _getAllItem : MutableLiveData<List<BasketStuff>> = MutableLiveData()
    val getAllItem : LiveData<List<BasketStuff>> = _getAllItem

    init {
        getBasketAllItem()
    }

    fun getBasketAllItem() = viewModelScope.launch {
        basketRepository.getAllBasketItem().collect {
            _getAllItem.value = it
        }
    }
}