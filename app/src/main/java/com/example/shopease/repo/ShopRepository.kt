package com.example.shopease.repo

import android.util.Log
import com.example.shopease.data.dto.toEntity
import com.example.shopease.data.local.CartItem
import com.example.shopease.data.local.ProductDao // Or ShopDao, check your naming!
import com.example.shopease.data.local.ProductEntity
import com.example.shopease.data.remote.ShopApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopRepository @Inject constructor(
    private val api: ShopApi,
    private val dao: ProductDao
) {

    val products: Flow<List<ProductEntity>> = dao.getAllProducts()
    val cartItems: Flow<List<CartItem>> = dao.getCartItems()
    val cartValue: Flow<Double?> = dao.getCartValue()

    suspend fun fetchProducts() {
        try {
            Log.d("DEBUG_SHOP", "Fetching from DummyJSON...")

            val response = api.getProducts()

            val remoteList = response.products

            val entityProducts = remoteList.map { it.toEntity() }


            dao.insertProducts(entityProducts)

        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

    suspend fun addToCart(product: ProductEntity) {
        val cartItem = CartItem(
            productId = product.id,
            name = product.title,
            price = product.price,
            imageUrl = product.imageUrl,
            quantity = 1
        )
        dao.insertCart(cartItem)
    }

    suspend fun updateQuantity(item: CartItem, newQuantity: Int) {
        if (newQuantity <= 0) {
            dao.deleteCartItem(item.productId)
        } else {
            dao.insertCart(item.copy(quantity = newQuantity))
        }
    }

    suspend fun removeFromCart(item: CartItem) {
        dao.deleteCartItem(item.productId)
    }
}