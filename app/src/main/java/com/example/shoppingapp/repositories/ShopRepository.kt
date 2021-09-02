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

//    suspend fun insertBookmarkShop(shopRanking: ShopRanking): Long {
//        return shopRankingDao.insertShop(shopRanking)
//    }
//    suspend fun deleteBookmarkShop(shopRanking: ShopRanking) {
//        return shopRankingDao.deleteShop(shopRanking)
//    }

    fun getAllBookmarkShops(): Flow<List<ShopRanking>>{
        return shopRankingDao.getAllBookmarkShops()
    }
}