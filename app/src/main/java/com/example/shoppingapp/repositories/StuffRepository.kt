package com.example.shoppingapp.repositories

import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.db.StuffDao
import com.example.shoppingapp.di.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StuffRepository @Inject constructor(
    val stuffDao: StuffDao
){

    suspend fun getStuffAPI(): List<Stuff> = RetrofitInstance.api.getAllProduct()

    fun getStuffs() : Flow<List<Stuff>>{
        return stuffDao.getAll()
    }

    fun getBest() : Flow<List<Stuff>>{
        return stuffDao.getBestCategory()
    }
    fun getClothes() : Flow<List<Stuff>>{
        return stuffDao.getClothesCategory()
    }
    fun getOnepiece() : Flow<List<Stuff>>{
        return stuffDao.getOnepieceCategory()
    }
    fun getPants() : Flow<List<Stuff>>{
        return stuffDao.getPantsCategory()
    }
    fun getSkirt() : Flow<List<Stuff>>{
        return stuffDao.getSkirtCategory()
    }
    fun getOuter() : Flow<List<Stuff>>{
        return stuffDao.getOuterCategory()
    }
    fun getShoes() : Flow<List<Stuff>>{
        return stuffDao.getShoesCategory()
    }
    fun getAccessories() : Flow<List<Stuff>>{
        return stuffDao.getAccessoriesCategory()
    }
}