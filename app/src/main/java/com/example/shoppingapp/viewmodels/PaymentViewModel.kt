package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.repositories.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    val paymentRepository: PaymentRepository
) : ViewModel(){

    private val _getAllItems : MutableLiveData<List<BasketStuff>> = MutableLiveData()
    val getAllItems : LiveData<List<BasketStuff>> = _getAllItems

//    private val _paymentItems : MutableLiveData<List<BasketStuff>> = MutableLiveData(mutableListOf())
//    val paymentItems : LiveData<List<BasketStuff>> = _paymentItems

    init {
        allBasketToPayment()
    }

    fun allBasketToPayment() =viewModelScope.launch {
        paymentRepository.getBasketToPaymentItems().collect {
            _getAllItems.value = it
        }
    }

    fun makeEmptyPayment(payment : List<BasketStuff>)=viewModelScope.launch{
        val list = mutableListOf<String>()
        for (item in payment){
            list.add(item.uid.toString())
        }
        withContext(Dispatchers.IO) {
            paymentRepository.makeEmptyPayment(list)
        }

    }
}