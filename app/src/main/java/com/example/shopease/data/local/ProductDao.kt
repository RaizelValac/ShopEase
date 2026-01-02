package com.example.shopease.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)


    @Query("SELECT * FROM cart_table")
    fun getCartItems(): Flow<List<CartItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(products: CartItem)

    @Query("DELETE FROM cart_table WHERE productId = :productId")
    suspend fun deleteCartItem(productId: Int)

    @Query("SELECT SUM(price * quantity) FROM cart_table")
    fun getCartValue() : Flow<Double?>


    @Query("SELECT EXISTS (SELECT 1 FROM cart_table WHERE productId = :productId)")
    fun isInCart(productId: Int): Flow<Boolean>

}