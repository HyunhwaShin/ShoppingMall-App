package com.example.shoppingapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.shoppingapp.db.AppDatabase
import com.example.shoppingapp.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.shoppingapp.other.Constants.KEY_NAME
import com.example.shoppingapp.other.Constants.KEY_EMAIL
import com.example.shoppingapp.other.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingDatabase(
        @ApplicationContext app: Context
    )= Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "shopping_db"
    ).build()

    @Provides
    fun provideStuffDao(database: AppDatabase) = database.stuffDao()

    @Provides
    fun provideShopRankingDao(database: AppDatabase) = database.shopRankingDao()

    @Provides
    fun provideDeliveryDao(database : AppDatabase) = database.deliveryDao()

    @Provides
    fun provideBasketStuffDao(database: AppDatabase) = database.basketStuffDao()

    @Singleton
    @Provides
    fun provideUserSharedPreferences(@ApplicationContext app:Context) : SharedPreferences =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPref : SharedPreferences) = sharedPref.getString(KEY_NAME,"") ?: ""

    @Singleton
    @Provides
    fun provideEmail(sharedPref: SharedPreferences) = sharedPref.getString(KEY_EMAIL,"") ?: ""

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref: SharedPreferences) =
        sharedPref.getBoolean(KEY_FIRST_TIME_TOGGLE, true)

}