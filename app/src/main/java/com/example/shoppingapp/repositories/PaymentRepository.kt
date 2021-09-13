package com.example.shoppingapp.repositories

import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.db.BasketStuffDao
import com.example.shoppingapp.db.Delivery
import com.example.shoppingapp.db.DeliveryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepository @Inject constructor(
    val deliveryDao: DeliveryDao
) {
//    fun getBasketToPaymentItems() : Flow<List<BasketStuff>> {
//         return basketStuffDao.getBasketToPayment()
//    }

//    fun makeEmptyPayment(uid : List<String>) = basketStuffDao.makeEmptyPayment(uid)
//
//    fun getPaymentItemPrice() : Flow<List<Int>> {
//        return basketStuffDao.getPaymentItemPrice()
//    }

    suspend fun insertDelivery(delivery: Delivery) = deliveryDao.insert(delivery)
}