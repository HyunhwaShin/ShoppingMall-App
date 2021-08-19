package com.example.shoppingapp.repositories

import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.db.StuffDao
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LikeRepository @Inject constructor(
        val stuffDao: StuffDao
){
    fun getLikeStuffs(): Flow<List<Stuff>>{
        return stuffDao.getLikeAll()
    }

    fun likeEdit(uid : List<String>) = stuffDao.likeEdit(uid)

}