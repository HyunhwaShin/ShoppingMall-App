package com.example.shoppingapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    @Query("SELECT * FROM payment ")
    fun getAllPayment() : Flow<List<Payment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<Payment>)
}