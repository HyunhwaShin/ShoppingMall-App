package com.example.shoppingapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketStuffDao {

    @Query("SELECT * FROM basketStuff ")
    fun getAllBasketItem() : Flow<List<BasketStuff>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(basketStuff : BasketStuff)
}