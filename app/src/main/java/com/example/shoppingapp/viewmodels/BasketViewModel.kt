package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.db.Stuff
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

    private val _basketToPaymentList : MutableLiveData<MutableList<BasketStuff>> = MutableLiveData(mutableListOf())
    val basketToPaymentList : LiveData<MutableList<BasketStuff>> = _basketToPaymentList

    private var getPrice : Int = 0

    private val _totalPrice : MutableLiveData<Int> = MutableLiveData()
    val totalPrice : LiveData<Int> = _totalPrice

    private val _isComplete: MutableLiveData<Boolean> = MutableLiveData()
    val isComplete: LiveData<Boolean> = _isComplete

    init {
        getBasketAllItem()
    }

    fun getBasketAllItem() = viewModelScope.launch {
        basketRepository.getAllBasketItem().collect {
            _getAllItem.value = it
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

    fun toggleBasketToPaymentItemList(isCheck: Boolean, basketStuff: BasketStuff) {
        val list = _basketToPaymentList.value!!

        if (isCheck) {
            list.add(basketStuff)
            _basketToPaymentList.value = list

        } else {
            list.remove(basketStuff)
            _basketToPaymentList.value = list
        }
    }

    fun goPaymentList(basketStuff: List<BasketStuff>)=viewModelScope.launch{

        val list = mutableListOf<String>()
        for (item in basketStuff){
            list.add(item.uid.toString())
        }
        withContext(Dispatchers.IO){
            basketRepository.goPayment(list)
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
    fun setIsComplete(value: Boolean){
        _isComplete.value = value
    }
}