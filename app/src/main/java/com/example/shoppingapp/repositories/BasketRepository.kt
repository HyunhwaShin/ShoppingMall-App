package com.example.shoppingapp.repositories

import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.db.BasketStuffDao
import com.example.shoppingapp.db.PaymentDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BasketRepository @Inject constructor(
    val basketStuffDao: BasketStuffDao
) {
    fun getAllBasketItem(): Flow<List<BasketStuff>> {
        return basketStuffDao.getAllBasketItem()
    }

    suspend fun deleteFromBasket(basketStuff: BasketStuff) = basketStuffDao.delete(basketStuff)

   // fun insertPayment(payment: List<String>) = paymentDao.insert(payment)
}