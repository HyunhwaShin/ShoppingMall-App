package com.example.shoppingapp.repositories

import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.db.StuffDao
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LikeRepository@Inject constructor(
        val stuffDao: StuffDao
){
    fun getLikeStuffs(id: Long): Flow<List<Stuff>>{
        return stuffDao.getLikeAll(id)
    }

    fun delete(stuff: Stuff){
        try{
            val thread = Thread(Runnable { stuffDao.delete(stuff) })
            thread.start()
        }catch (e: Exception){}
    }
}