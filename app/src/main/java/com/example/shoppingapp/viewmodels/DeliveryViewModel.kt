package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.Delivery
import com.example.shoppingapp.db.User
import com.example.shoppingapp.repositories.DeliveryRepository
import com.example.shoppingapp.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliveryViewModel @Inject constructor(
        val deliveryRepository: DeliveryRepository,
        val userRepository: UserRepository
) : ViewModel() {
    private val _getAllDeliveries : MutableLiveData<List<Delivery>> = MutableLiveData()
    val getAllDeliveries : LiveData<List<Delivery>>
        get() =_getAllDeliveries

    private val _user : MutableLiveData<User> = MutableLiveData()
    val user : LiveData<User>
        get() = _user



    init {
        setUser()
        getAllDelivery()
    }

    fun getAllDelivery() = viewModelScope.launch {
        deliveryRepository.getAllDelivery().collect {
            _getAllDeliveries.value = it
        }
    }
    fun setUser()=viewModelScope.launch {
        userRepository.userInformation().collect{
            _user.value = it
        }
    }
}