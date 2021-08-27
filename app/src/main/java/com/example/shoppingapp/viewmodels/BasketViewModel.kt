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

    private var getPrice : Int = 0

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

    //price
    fun toggleGoPaymentItemList(isCheck: Boolean, basketStuff: BasketStuff) {
        val list = _goPaymentList.value!!

        if (isCheck) {
            list.add(basketStuff)
            _goPaymentList.value = list
        } else {
            list.remove(basketStuff)
            _goPaymentList.value = list
        }
    }

    fun toggleCalculateTotalPrice(isCheck: Boolean, value: Int){
        if(isCheck){
            getPrice= getPrice.plus(value)
            totalPrice(getPrice)
        }else{
            getPrice= getPrice.minus(value)
            totalPrice(getPrice)
        }
    }

    fun totalPrice (value : Int){
        _totalPrice.value = value
    }

    fun deleteItem (basketStuff : BasketStuff)= viewModelScope.launch {
        withContext(Dispatchers.IO){
            basketRepository.deleteFromBasket(basketStuff)
        }
    }

    //미완성
    fun goPayment(payment: List<Payment>)=viewModelScope.launch{
        val list = mutableListOf<String>()
        for (item in payment){
            list.add(item.uid)
        }
        withContext(Dispatchers.IO){
           // basketRepository.insertPayment(list)
        }
    }

}