package com.example.shoppingapp.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryDao {
    @Query("SELECT * FROM delivery")
    fun getAll() : Flow<List<Delivery>>

}