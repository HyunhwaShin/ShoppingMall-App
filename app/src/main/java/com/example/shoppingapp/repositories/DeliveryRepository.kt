package com.example.shoppingapp.repositories

import com.example.shoppingapp.db.Delivery
import com.example.shoppingapp.db.DeliveryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeliveryRepository@Inject constructor(
        val deliveryDao: DeliveryDao
){
    fun getAllDelivery() : Flow<List<Delivery>>{
        return deliveryDao.getAll()
    }
}