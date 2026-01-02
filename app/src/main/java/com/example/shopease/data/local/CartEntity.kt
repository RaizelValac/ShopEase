package com.example.shopease.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class CartItem(
    @PrimaryKey val productId: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val quantity: Int
)