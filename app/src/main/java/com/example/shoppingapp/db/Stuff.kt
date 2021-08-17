package com.example.shoppingapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="stuff")
data class Stuff(@PrimaryKey val uid: String,
                 @ColumnInfo(name ="stuffImage")val product_img: String = "",
                 @ColumnInfo(name ="shopId")val shop_uid: String = "",
                 @ColumnInfo(name ="shopName") val shop_name: String= "",
                 @ColumnInfo(name ="stuffName") val product_name: String= "",
                 @ColumnInfo(name ="stuffCategory") val product_category: String= "",
                 @ColumnInfo(name ="stuffPrice") val product_price: Int? = null,
                 @ColumnInfo(name ="stuffSize") val product_size: String= "",
                 @ColumnInfo(name ="stuffColor") val product_color: String= "",
                 @ColumnInfo(name ="stuffLink") val product_link: String= "",
                 @ColumnInfo(name ="checkBox") val checkBox: Boolean = false,
                 @ColumnInfo(name ="likeButton") val isLike: Boolean = false,
                 @ColumnInfo(name ="productLikeAmount") val proudct_like: Int = 0

):Serializable

