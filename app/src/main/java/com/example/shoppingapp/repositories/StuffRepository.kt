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
) {

    suspend fun getStuffAPI(): List<Stuff> = RetrofitInstance.api.getAllProduct()

    fun getStuffAll() = stuffDao.getAll()

    fun insert(stuff: List<Stuff>) = stuffDao.insert(stuff)

    fun update(stuff: Stuff) = stuffDao.update(stuff.uid)

    fun updateCancel(stuff: Stuff) = stuffDao.updateCancel(stuff.uid)
}
