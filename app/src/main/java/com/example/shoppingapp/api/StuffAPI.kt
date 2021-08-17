package com.example.shoppingapp.api

import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.model.ProductLike
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface StuffAPI {
    @GET("product")
    suspend fun getAllProduct():List<Stuff>

    @POST("product_like")
    suspend fun updateProductLike(productLike: ProductLike)
}
