package com.example.shoppingapp.db

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URL

@Entity(tableName="stuff")
data class Stuff(@PrimaryKey(autoGenerate = true) val stuffId : Int? =null,
                // @ColumnInfo(name ="stuffImage")val stuffImage: URL? = null,
                 @ColumnInfo(name ="shopName") val shopName : String= "",
                 @ColumnInfo(name ="stuffName") val stuffName : String= "",
                 @ColumnInfo(name ="stuffPrice") val stuffPrice : String= "",
                 @ColumnInfo(name ="checkBox") val checkBox : Boolean = false,
                 @ColumnInfo(name ="likeButton") val likeButton : Boolean = false
)
