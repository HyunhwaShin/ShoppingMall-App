package com.example.shoppingapp.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName="delivery")
data class Delivery(
        @PrimaryKey(autoGenerate = true) val deliveryId : Int? = null,
        @ColumnInfo(name ="deliveryDate") val deliveryDate : String = "",
        @ColumnInfo(name ="deliveryStatus") val deliveryStatus : String = "",
        @ColumnInfo(name ="userName") val userName : String = "",
        @ColumnInfo(name ="phoneNumber") val phoneNumber : String = "",
        @ColumnInfo(name ="address") val address : String = "",
        @ColumnInfo(name ="memo") val memo : String = "",
        @ColumnInfo(name ="paymentMethod") val paymentMethod : String = "",
        @ColumnInfo(name = "basketStuffList") val basketStuff: List<BasketStuff>? = null
): Parcelable
