package com.example.shoppingapp.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketStuffDao {

    @Query("SELECT * FROM basketStuff ")
    fun getAllBasketItem() : Flow<List<BasketStuff>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(basketStuff : BasketStuff)

    @Delete
    suspend fun delete(basketStuff: BasketStuff)

}
