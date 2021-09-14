package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.db.Delivery
import com.example.shoppingapp.other.SingleLiveEvent
import com.example.shoppingapp.repositories.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    val paymentRepository: PaymentRepository
) : ViewModel(){

    private val _setAllItems : MutableLiveData<List<BasketStuff>> = MutableLiveData()
    val setAllItems : LiveData<List<BasketStuff>> = _setAllItems

    private val _setPrice : MutableLiveData<Int> = MutableLiveData()
    val setPrice : LiveData<Int> = _setPrice

    private val _setName : MutableLiveData<String> = MutableLiveData("")

    private val _setPhone : MutableLiveData<String> = MutableLiveData("")

    private val _setAddress : MutableLiveData<String> = MutableLiveData("")

    private val _setMemo : MutableLiveData<String> = MutableLiveData("")

    private val _setDate : MutableLiveData<String> = MutableLiveData("")

    private val _setButton : MutableLiveData<String> = MutableLiveData("")
    val setButton: LiveData<String>
        get() = _setButton

    private val _isComplete = SingleLiveEvent<Boolean>()
    val isComplete : LiveData<Boolean>
        get() = _isComplete

    init {

    }

    fun setAllItem(allItem: List<BasketStuff>){
        _setAllItems.value = allItem
    }

    fun calculateTotalPrice(priceList: List<BasketStuff>) {
        var price : Int = 0
        for(p in priceList){
            price += p.product_price!!
        }
        _setPrice.value = price
    }

    fun setName(value : String){
        _setName.value = value
    }

    fun setPhone(value : String){
        _setPhone.value = value
    }

    fun setAddress(value : String){
        _setAddress.value = value
    }

    fun setMemo(value : String){
        _setMemo.value = value
    }

    fun setButton(value: String){
        _setButton.value = value
    }

    fun setIsComplete(){
        _isComplete.call()
    }

    fun todayDate(){
        val now = LocalDateTime.now()
        val date = DateTimeFormatter.ISO_DATE
        val todayDate = now.format(date)
        _setDate.value = todayDate
    }

    fun insert(delivery: Delivery) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            paymentRepository.insertDelivery(delivery)
        }
    }

    fun insertCheck(){
        if(!(_setName.value!!.isEmpty() || _setAddress.value!!.isEmpty() ||_setPhone.value!!.isEmpty() ||_setMemo.value!!.isEmpty() ||_setButton.value!!.isEmpty() )){
            val delivery = Delivery(null,_setDate.value!!,"status",_setName.value!!,_setPhone.value!!,
            _setAddress.value!!,_setMemo.value!!,_setButton.value!!,true, _setAllItems.value!!)
            insert(delivery)
            _isComplete.value = true
        }else{
            _isComplete.value = false

        }
    }
}