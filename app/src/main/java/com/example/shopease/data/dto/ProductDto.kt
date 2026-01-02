package com.example.shopease.data.dto

import com.example.shopease.data.local.ProductEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ProductResponse(
    @Json(name = "products") val products: List<ProductDto>
)


@JsonClass(generateAdapter = true)
data class ProductDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "price") val price: Double,
    @Json(name = "description") val description: String,
    @Json(name = "category") val category: String,
    @Json(name = "thumbnail") val thumbnail: String,
    @Json(name = "rating") val rating: Double
)

fun ProductDto.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        imageUrl = thumbnail,
        rating = rating
    )
}