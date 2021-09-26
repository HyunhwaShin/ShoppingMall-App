package com.example.shoppingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppingapp.other.Constants.DATABASE_NAME

@Database(
        entities = [Stuff::class , ShopRanking::class, Delivery::class, BasketStuff::class, User::class],
        version = 1,
        exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class AppDatabase :RoomDatabase(){
    abstract fun stuffDao() : StuffDao
    abstract fun shopRankingDao() : ShopRankingDao
    abstract fun deliveryDao() : DeliveryDao
    abstract fun basketStuffDao() : BasketStuffDao
    abstract fun userDao() : UserDao

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
    }
}