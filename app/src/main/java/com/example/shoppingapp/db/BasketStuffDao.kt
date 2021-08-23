package com.example.shoppingapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketStuffDao {

    @Query("SELECT * FROM basketStuff ")
    fun getBasketItem() : Flow<BasketStuff>
}