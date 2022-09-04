package com.example.composepracticeplants.features.mainScreen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composepracticeplants.R

sealed class BottomNavItems(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object HomeScreen: BottomNavItems(HOME_ROUTE, R.string.home_screen, Icons.Outlined.Home)
    object FavoritesScreen: BottomNavItems(FAVORITES_ROUTE, R.string.favorites, Icons.Outlined.Favorite)
    object ShoppingScreen: BottomNavItems(SHOPPING_CART_ROUTE, R.string.shopping_cart, Icons.Outlined.ShoppingCart)
    object OffersScreen: BottomNavItems(OFFERS_ROUTE, R.string.offer_screen, Icons.Filled.Star)
}
