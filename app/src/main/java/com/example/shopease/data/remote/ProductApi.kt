package com.example.shopease.data.remote

import com.example.shopease.data.dto.ProductResponse
import retrofit2.http.GET

interface ShopApi {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}