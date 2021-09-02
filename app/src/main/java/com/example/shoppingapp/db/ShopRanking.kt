package com.example.shoppingapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="shopRanking")
data class ShopRanking(@PrimaryKey val uid: String,
                       @ColumnInfo(name ="rankingShopName") val shop_name : String= "",
                       @ColumnInfo(name ="bookmarkButton") val isBookmark : Boolean = false,
                       @ColumnInfo(name ="bookmark") val bookmark: String,
                       @ColumnInfo(name ="url") val url: String
):Serializable
