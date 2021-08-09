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
        getDummyDelivery()
    }

    fun getAllDelivery() = viewModelScope.launch {
        deliveryRepository.getAllDelivery().collect {
            _getAllDeliveries.value = it
        }
    }

    fun getDummyDelivery()= viewModelScope.launch {
        val deliveryList = mutableListOf<Delivery>()
        deliveryList.add(Delivery(0,"2021.08.06","원피스 가게","보라색 꽃 원피스","배송완료"))
        deliveryList.add(Delivery(1,"2021.08.07","문구점","가지 필통","배송중"))
        deliveryList.add(Delivery(2,"2021.08.08","도자기 전문점","물개 물컵","배송중"))
        deliveryList.add(Delivery(3,"2021.08.08","컴퓨터의 모든 것","버티컬 마우스","상품 준비중"))
        _getDummy.value = deliveryList
    }

}