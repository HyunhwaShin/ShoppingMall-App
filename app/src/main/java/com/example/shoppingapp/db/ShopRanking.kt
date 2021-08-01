package com.example.shoppingapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="shopRanking")
data class ShopRanking(@PrimaryKey(autoGenerate = true) val shopId : Int? =null,
                       @ColumnInfo(name ="rankingShopName") val rankingShopName : String= "",
                       @ColumnInfo(name ="bookmarkButton") val bookmarkButton : Boolean = false
)
