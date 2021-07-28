package com.example.shoppingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
        entities = [Stuff::class],
        version = 1,
        exportSchema = false
)
abstract class StuffDatabase :RoomDatabase(){
    abstract fun stuffDao() : StuffDao

    companion object{
        private var INSTANCE : StuffDatabase? = null

        fun getInstance(context: Context) : StuffDatabase?{
            if(INSTANCE == null){
                synchronized(StuffDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            StuffDatabase::class.java, "Stuff")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }
    }
}