package com.example.shoppingapp.db

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StuffDao{
    @Query("SELECT * FROM stuff")
    fun getAll() : Flow<List<Stuff>>
}