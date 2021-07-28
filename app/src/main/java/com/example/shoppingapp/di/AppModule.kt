package com.example.shoppingapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.shoppingapp.db.StuffDatabase
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
    fun provideStuffDatabase(
        @ApplicationContext app: Context
    )= Room.databaseBuilder(
        app,
        StuffDatabase::class.java,
        "stuff_db"
    ).build()

    @Provides
    fun provideStuffDao(database: StuffDatabase) = database.stuffDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app:Context) : SharedPreferences =
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