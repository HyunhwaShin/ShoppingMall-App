package com.example.shoppingapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "basketStuff")
data class BasketStuff(@PrimaryKey(autoGenerate = true) val uid: Long? = null,
                       @ColumnInfo(name ="stuffImage")val product_img: String = "",
                       @ColumnInfo(name ="stuffName") val product_name: String= "",
                       @ColumnInfo(name ="stuffPrice") val product_price: Int? = null,
                       @ColumnInfo(name ="stuffSize") val product_size: String= "",
                       @ColumnInfo(name ="stuffColor") val product_color: String= "",
                       @ColumnInfo(name ="checkBox") val checkBox: Boolean = false,
                       @ColumnInfo(name ="isPayment") val isPayment: Boolean = false
): Serializable
