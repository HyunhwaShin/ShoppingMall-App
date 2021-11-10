package com.example.shoppingapp.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName="delivery")
data class Delivery(
        @PrimaryKey(autoGenerate = true) val orderNumber : Int? = null,
        @ColumnInfo(name ="orderDate") val orderDate : String = "",
        @ColumnInfo(name ="userEmail") val userEmail : String = "",
        @ColumnInfo(name ="name") val name : String = "",
        @ColumnInfo(name ="phoneNumber") val phoneNumber : String = "",
        @ColumnInfo(name ="addr") val addr : String = "",
        @ColumnInfo(name ="memo") val memo : String = "",
        @ColumnInfo(name ="payType") val payType : String = "",
        @ColumnInfo(name ="price") val price : String = "",
        @ColumnInfo(name ="orderInfo") val orderInfo : String = "",
        @ColumnInfo(name ="isPayment") val isPayment: Boolean = false,
        @ColumnInfo(name ="shopName") val shopName: String = "",
        @ColumnInfo(name ="img") val img: String = ""
): Parcelable
