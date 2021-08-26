package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.db.Payment
import com.example.shoppingapp.repositories.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    val basketRepository: BasketRepository
): ViewModel() {
    private val _getAllItem : MutableLiveData<List<BasketStuff>> = MutableLiveData()
    val getAllItem : LiveData<List<BasketStuff>> = _getAllItem

    private val _goPaymentList : MutableLiveData<MutableList<BasketStuff>> = MutableLiveData(mutableListOf())
    val goPaymentList : LiveData<MutableList<BasketStuff>> = _goPaymentList

    private val _getPrice : MutableLiveData<Int> = MutableLiveData()
    val getPrice : LiveData<Int> = _getPrice

    private val _totalPrice : MutableLiveData<Int> = MutableLiveData()
    val totalPrice : LiveData<Int> = _totalPrice

    init {
        getBasketAllItem()
    }

    fun getBasketAllItem() = viewModelScope.launch {
        basketRepository.getAllBasketItem().collect {
            _getAllItem.value = it
        }
    }

    fun toggleGoPaymentItemList(isCheck: Boolean, basketStuff: BasketStuff){
        val list = _goPaymentList.value!!
        if(isCheck){
            list.add(basketStuff)
            _goPaymentList.value = list
        }else{
            list.remove(basketStuff)
            _goPaymentList.value = list
        }
    }

    //check 보류!!
    fun goPayment(payment: List<Payment>)=viewModelScope.launch{
        val list = mutableListOf<String>()
        for (item in payment){
            list.add(item.uid)
        }
        withContext(Dispatchers.IO){
           // basketRepository.insertPayment(list)
        }
    }
    fun toggleCalculateTotalPrice(isCheck: Boolean, value: Int){
        if(isCheck){
            _getPrice.value = _getPrice.value?.plus(value)
        }else{
            _getPrice.value = _getPrice.value?.minus(value)
        }
//        _getPrice.value = value
    }

    fun totalPrice (value : Int){
        _totalPrice.value = _getPrice.value
    }
}