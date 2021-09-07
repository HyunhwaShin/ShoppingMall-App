package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.Delivery
import com.example.shoppingapp.repositories.DeliveryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliveryViewModel @Inject constructor(
        val deliveryRepository: DeliveryRepository
) : ViewModel() {
    private val _getAllDeliveries : MutableLiveData<List<Delivery>> = MutableLiveData()
    val getAllDeliveries : LiveData<List<Delivery>> =_getAllDeliveries

    private val _getDummy : MutableLiveData<List<Delivery>> = MutableLiveData(mutableListOf())
    val getDummy : LiveData<List<Delivery>> = _getDummy

    init {
    }

    fun getAllDelivery() = viewModelScope.launch {
        deliveryRepository.getAllDelivery().collect {
            _getAllDeliveries.value = it
        }
    }


}