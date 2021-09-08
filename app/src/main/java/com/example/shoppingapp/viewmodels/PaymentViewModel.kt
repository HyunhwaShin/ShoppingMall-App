package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.db.Delivery
import com.example.shoppingapp.repositories.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    val paymentRepository: PaymentRepository
) : ViewModel(){

    private val _getAllItems : MutableLiveData<List<BasketStuff>> = MutableLiveData()
    val getAllItems : LiveData<List<BasketStuff>> = _getAllItems

    private val _getPrice : MutableLiveData<List<Int>> = MutableLiveData(listOf())
    val getPrice : LiveData<List<Int>> = _getPrice

    private val _getName : MutableLiveData<String> = MutableLiveData("")

    private val _getPhone : MutableLiveData<String> = MutableLiveData("")

    private val _getAddress : MutableLiveData<String> = MutableLiveData("")

    private val _getMemo : MutableLiveData<String> = MutableLiveData("")

    private val _getDate : MutableLiveData<String> = MutableLiveData("")

    private val _getButton : MutableLiveData<String> = MutableLiveData("")

    private val _isComplete: MutableLiveData<Boolean> = MutableLiveData()
    val isComplete: LiveData<Boolean> = _isComplete

    init {
        allBasketToPayment()
        getAllPrice()
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

    fun getAllPrice() = viewModelScope.launch {
        paymentRepository.getPaymentItemPrice().collect{
            _getPrice.value =it
        }
    }

    fun calculateTotalPrice(priceList: List<Int>): Int {
        var price : Int = 0
        for(p in priceList){
            price += p
        }
        return price
    }

    fun getName(value : String){
        _getName.value = value
    }

    fun getPhone(value : String){
        _getPhone.value = value
    }

    fun getAddress(value : String){
        _getAddress.value = value
    }

    fun getMemo(value : String){
        _getMemo.value = value
    }

    fun getButton(value: String){
        _getButton.value = value
    }

    fun setIsComplete(value: Boolean){
        _isComplete.value = value
    }

    fun todayDate(){
        val now = LocalDateTime.now()
        val date = DateTimeFormatter.ISO_DATE
        val todayDate = now.format(date)
        _getDate.value = todayDate
    }

    fun insert(delivery: Delivery) = viewModelScope.launch{
        withContext(Dispatchers.IO){
            paymentRepository.insertDelivery(delivery)
        }
    }

    fun insertCheck(){
        if(!(_getName.value!!.isEmpty() || _getAddress.value!!.isEmpty() ||_getPhone.value!!.isEmpty() ||_getMemo.value!!.isEmpty() ||_getButton.value!!.isEmpty() )){
//            val delivery = Delivery(null,_getDate.value!!,"status",_getName.value!!,_getPhone.value!!,
//            _getAddress.value!!,_getMemo.value!!,_getButton.value!!,"basket")
//            insert(delivery)
            _isComplete.value = true
        }else{
            //_isComplete.value = false

        }
    }
}