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

}