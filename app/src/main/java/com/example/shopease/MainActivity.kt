package com.example.shopease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shopease.navigation.CartScreen
import com.example.shopease.navigation.ShopScreen

import com.example.shopease.ui.CartScreen

import com.example.shopease.ui.StoreScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopNavigation()
        }
    }
}

@Composable
fun ShopNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ShopScreen) {


        composable<ShopScreen> {
            StoreScreen(
                onNavigateToCart = {
                    navController.navigate(CartScreen)
                }
            )
        }


        composable<CartScreen> {
            CartScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}