package com.example.shoppingapp.repositories

import com.example.shoppingapp.db.ShopRanking
import com.example.shoppingapp.db.ShopRankingDao
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.di.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShopRepository @Inject constructor(
    val shopRankingDao: ShopRankingDao
){

    suspend fun getShopsAPI(): List<ShopRanking> = RetrofitInstance.api.getShops()

    fun getAllBookmarkShops(): Flow<List<ShopRanking>>{
        return shopRankingDao.getAllBookmarkShops()
    }

    fun insertBookmark(shopRanking: ShopRanking) = shopRankingDao.insertBookmark(shopRanking.uid)

    fun deleteBookmark(shopRanking: ShopRanking) = shopRankingDao.deleteBookmark(shopRanking.uid)

}