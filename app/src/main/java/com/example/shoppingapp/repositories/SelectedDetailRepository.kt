package com.example.shoppingapp.repositories


import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.db.BasketStuffDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectedDetailRepository@Inject constructor(
    val basketStuffDao: BasketStuffDao
){

    suspend fun insert(basketStuff: BasketStuff){
        basketStuffDao.insert(basketStuff)
    }

}