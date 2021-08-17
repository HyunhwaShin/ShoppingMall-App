package com.example.shoppingapp.model

import androidx.room.Entity
import java.io.Serializable

@Entity(tableName="stuffResponse")
data class StuffResponseItem(
    val product_color: String,
    val product_category: String,
    val product_img: String,
    val product_link: String,
    val product_name: String,
    val product_price: Int,
    val product_size: String,
    val proudct_like: Int,
    val shop_uid: String,
    val uid: String
): Serializable