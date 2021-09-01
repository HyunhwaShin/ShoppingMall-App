package com.example.shoppingapp.repositories

import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.db.BasketStuffDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepository @Inject constructor(
    val basketStuffDao: BasketStuffDao
) {
    fun getBasketToPaymentItems() : Flow<List<BasketStuff>> {
         return basketStuffDao.getBasketToPayment()
    }

    fun makeEmptyPayment(uid : List<String>) = basketStuffDao.makeEmptyPayment(uid)

    //fun getPaymentItemPrice(price : List<Int>) = basketStuffDao.getPaymentItemPrice(price)
    fun getPaymentItemPrice() : Flow<List<Int>> {
        return basketStuffDao.getPaymentItemPrice()
    }
}