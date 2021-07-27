package com.example.shoppingapp.di

import android.content.Context
import androidx.room.Room
import com.example.shoppingapp.db.StuffDatabase
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
}