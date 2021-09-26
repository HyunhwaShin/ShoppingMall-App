package com.example.shoppingapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="user")
data class User (
    @PrimaryKey
    @ColumnInfo(name = "userEmail")
    val email : String = "",
    @ColumnInfo(name = "userName") val name : String = "",
)