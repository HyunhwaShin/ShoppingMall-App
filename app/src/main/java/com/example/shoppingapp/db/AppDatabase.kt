package com.example.shoppingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppingapp.other.Constants.DATABASE_NAME

@Database(
        entities = [Stuff::class , ShopRanking::class],
        version = 1,
        exportSchema = false
)
abstract class AppDatabase :RoomDatabase(){
    abstract fun stuffDao() : StuffDao
    abstract fun shopRankingDao() : ShopRankingDao

    companion object{
        @Volatile private var instance : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                    }
                )
                .build()
        }

//        fun getInstance(context: Context) : AppDatabase?{
//            if(INSTANCE == null){
//                synchronized(AppDatabase::class){
//                    INSTANCE = Room.databaseBuilder(context.applicationContext,
//                            AppDatabase::class.java, "Stuff")
//                            .fallbackToDestructiveMigration()
//                            .build()
//                }
//            }
//            return INSTANCE
//        }
    }
}