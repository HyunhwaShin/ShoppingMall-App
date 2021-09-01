package com.example.shoppingapp.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketStuffDao {

    @Query("SELECT * FROM basketStuff")
    fun getAllBasketItem() : Flow<List<BasketStuff>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(basketStuff : BasketStuff)

    @Delete
    suspend fun delete(basketStuff: BasketStuff)

    @Query("UPDATE basketStuff SET isPayment = 1 WHERE uid in (:uid)")
    fun goPayment(uid: List<String>)

    @Query("SELECT * FROM basketStuff WHERE isPayment = 1")
    fun getBasketToPayment() : Flow<List<BasketStuff>>

    @Query("UPDATE basketStuff SET isPayment = 0 WHERE uid in (:uid)")
    fun makeEmptyPayment(uid : List<String>)

    @Query("SELECT stuffPrice FROM basketStuff WHERE isPayment = 1")
    fun getPaymentItemPrice() : Flow<List<Int>>

}
