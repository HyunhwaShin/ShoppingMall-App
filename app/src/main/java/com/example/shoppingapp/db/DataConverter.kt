package com.example.shoppingapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class DataConverter {

    @TypeConverter
    fun fromBasketStuffList(basketStuff: List<BasketStuff>): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<BasketStuff>>() {}.type
        return gson.toJson(basketStuff, type)
    }

    @TypeConverter
    fun toBasketStuffList(basketStuffString: String): List<BasketStuff>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<BasketStuff>>() {}.type
        return gson.fromJson<List<BasketStuff>>(basketStuffString, type)
    }
}