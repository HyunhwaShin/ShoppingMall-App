package com.example.shoppingapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="stuff")
data class Stuff(@PrimaryKey(autoGenerate = true) val stuffId: Int? =null,
                // @ColumnInfo(name ="stuffImage")val stuffImage: URL? = null,
                 @ColumnInfo(name ="shopName") val shopName: String= "",
                 @ColumnInfo(name ="stuffName") val stuffName: String= "",
                 @ColumnInfo(name ="stuffCategory") val stuffCategory: String= "",
                 @ColumnInfo(name ="stuffPrice") val stuffPrice: Int? = null,
                 @ColumnInfo(name ="stuffSize") val stuffSize: String= "",
                 @ColumnInfo(name ="stuffColor") val stuffColor: String= "",
                 @ColumnInfo(name ="stuffLink") val stuffLink: String= "",
                 @ColumnInfo(name ="checkBox") val checkBox: Boolean = false,
                 @ColumnInfo(name ="likeButton") val likeButton: Boolean = false
)
