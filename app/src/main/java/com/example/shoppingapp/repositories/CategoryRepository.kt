package com.example.shoppingapp.repositories

import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.db.StuffDao
import com.example.shoppingapp.di.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    val stuffDao: StuffDao
){
    suspend fun getBestAPI(): List<Stuff> = RetrofitInstance.api.getBest()
    suspend fun getClothesAPI(): List<Stuff> = RetrofitInstance.api.getClothes()
    suspend fun getOnepieceAPI(): List<Stuff> = RetrofitInstance.api.getOnepiece()
    suspend fun getPantsAPI(): List<Stuff> = RetrofitInstance.api.getPants()
    suspend fun getSkirtAPI(): List<Stuff> = RetrofitInstance.api.getSkirt()
    suspend fun getOuterAPI(): List<Stuff> = RetrofitInstance.api.getOuter()
    suspend fun getShoesAPI(): List<Stuff> = RetrofitInstance.api.getShoes()
    suspend fun getAccessoriesAPI(): List<Stuff> = RetrofitInstance.api.getAccessories()

}