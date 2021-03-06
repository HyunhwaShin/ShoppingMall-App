package com.example.shoppingapp.api

import com.example.shoppingapp.db.*
import com.example.shoppingapp.model.ProductLike
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface StuffAPI {
    //home
    @GET("product")
    suspend fun getAllProduct():List<Stuff>

    //category
    @GET("product/category?type=best")
    suspend fun getBest():List<Stuff>

    @GET("product/category?type=상의")
    suspend fun getClothes():List<Stuff>

    @GET("product/category?type=원피스")
    suspend fun getOnepiece():List<Stuff>

    @GET("product/category?type=바지")
    suspend fun getPants():List<Stuff>

    @GET("product/category?type=치마")
    suspend fun getSkirt():List<Stuff>

    @GET("product/category?type=아우터")
    suspend fun getOuter():List<Stuff>

    @GET("product/category?type=신발")
    suspend fun getShoes():List<Stuff>

    @GET("product/category?type=악세서리")
    suspend fun getAccessories():List<Stuff>

    //shop
    @GET("store")
    suspend fun getShops():List<ShopRanking>

    //like
    @POST("product_like")
    suspend fun postLikeCount(productLike : ProductLike)

    //user
    @POST("registerUser")
    suspend fun postUser(user: User) :String

    //shop
    @POST("store_like")
    suspend fun storeLike(storeLike : StoreLikeDto) :String

    @POST("store_delete")
    suspend fun storeDelete(storeLike : StoreLikeDto) :String

    @GET("myStore")
    suspend fun myStore(email : String) : List<ShopRankingDao>


}
