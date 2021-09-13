package com.example.shoppingapp.repositories

import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.db.BasketStuffDao
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

   // fun goPayment(uid : List<String>) = basketStuffDao.goPayment(uid)

}