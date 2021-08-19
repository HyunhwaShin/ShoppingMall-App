package com.example.shoppingapp.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StuffDao{
    @Query("SELECT * FROM stuff")
    fun getAll() : Flow<List<Stuff>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item : List<Stuff>)

    @Query("UPDATE stuff SET likeButton = 1 WHERE uid = :uid")
    fun update(uid : String)

    @Query("UPDATE stuff SET likeButton = 0 WHERE uid = :uid")
    fun updateCancel(uid: String)

    @Query("UPDATE stuff SET likeButton = 0 WHERE uid in (:uid)")
    fun likeEdit(uid : List<String>)

    //category
    @Query("SELECT * FROM stuff WHERE stuffCategory = 'best'")
    fun getBestCategory() : Flow<List<Stuff>>

    @Query("SELECT * FROM stuff WHERE stuffCategory = 'clothes'")
    fun getClothesCategory() : Flow<List<Stuff>>

    @Query("SELECT * FROM stuff WHERE stuffCategory = 'onepiece'")
    fun getOnepieceCategory() : Flow<List<Stuff>>

    @Query("SELECT * FROM stuff WHERE stuffCategory = 'pants'")
    fun getPantsCategory() : Flow<List<Stuff>>

    @Query("SELECT * FROM stuff WHERE stuffCategory = 'skirt'")
    fun getSkirtCategory() : Flow<List<Stuff>>

    @Query("SELECT * FROM stuff WHERE stuffCategory = 'outer'")
    fun getOuterCategory() : Flow<List<Stuff>>

    @Query("SELECT * FROM stuff WHERE stuffCategory = 'shoes'")
    fun getShoesCategory() : Flow<List<Stuff>>

    @Query("SELECT * FROM stuff WHERE stuffCategory = 'accessories'")
    fun getAccessoriesCategory() : Flow<List<Stuff>>


    //like
    @Query("SELECT * FROM stuff WHERE likeButton =1")
    fun getLikeAll() : Flow<List<Stuff>>

    @Delete
    fun delete(stuff: List<Stuff>)

    //detailStuff
//    @Query("SELECT stuffLink FROM stuff")
//    fun getStuffLink() : Flow<List<Stuff>>

}