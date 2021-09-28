package com.example.shoppingapp.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopRankingDao {
    @Query("SELECT * FROM shopRanking")
    fun getAll() : Flow<List<ShopRanking>>

    @Query("SELECT * FROM shopRanking WHERE bookmarkButton = 1")
    fun getAllBookmarkShops() : Flow<List<ShopRanking>>

    @Query("UPDATE shopRanking SET bookmarkButton = 1 WHERE uid = :uid")
    fun insertBookmark(uid: String)

    @Query("UPDATE shopRanking SET bookmarkButton = 0 WHERE uid = :uid")
    fun deleteBookmark(uid: String)

}