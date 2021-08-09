package com.example.shoppingapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="delivery")
data class Delivery(
        @PrimaryKey(autoGenerate = true) val deliveryId : Int? = null,
        @ColumnInfo(name ="deliveryDate") val deliveryDate : String = "",
        @ColumnInfo(name ="deliveryShop") val deliveryShop : String = "",
        @ColumnInfo(name ="deliveryStuff") val deliveryStuff : String = "",
        @ColumnInfo(name ="deliveryStatus") val deliveryStatus : String = ""
)
