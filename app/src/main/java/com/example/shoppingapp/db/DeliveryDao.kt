package com.example.shoppingapp.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryDao {
    @Query("SELECT * FROM delivery")
    fun getAll() : Flow<List<Delivery>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(delivery: Delivery)

    @Query("UPDATE delivery SET isPayment = 1 WHERE uid in (:uid)")
    fun goPayment(uid: List<String>)
}