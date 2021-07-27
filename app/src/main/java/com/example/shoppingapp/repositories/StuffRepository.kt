package com.example.shoppingapp.repositories

import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.db.StuffDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StuffRepository @Inject constructor(
    val stuffDao: StuffDao
){
    fun getStuffs() : Flow<List<Stuff>>{
        return stuffDao.getAll()
    }
}