package com.example.shoppingapp.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopRankingDao {
    @Query("SELECT * FROM shopRanking")
    fun getAll() : Flow<List<ShopRanking>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShop(shopRanking: ShopRanking) : Long

    @Delete
    suspend fun deleteShop(shopRanking: ShopRanking)


    @Query("SELECT * FROM shopRanking WHERE bookmarkButton = 1")
    fun getAllBookmarkShops() : Flow<List<ShopRanking>>
}