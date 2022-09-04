package com.example.composepracticeplants.features.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.composepracticeplants.domain.Plants
import com.example.composepracticeplants.domain.Properties
import com.example.composepracticeplants.domain.PurchaseInfo
import com.example.composepracticeplants.features.homeScreen.HomeTab
import com.example.composepracticeplants.features.purchaseScreen.PurchaseScreen
import com.example.composepracticeplants.ui.theme.NavColor

@Composable
fun MainScaffold(
    modifier: Modifier,
    navController: NavHostController,
    items: List<BottomNavItems>,
    plants: Plants,
) {
    Scaffold(
        bottomBar =
        {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { bottomNavItem ->
                    BottomNavigationItem(
                        selected = currentDestination?.route == bottomNavItem.route,
                        label = { Text(stringResource(bottomNavItem.resourceId)) },
                        icon = {
                            Icon(
                                bottomNavItem.icon,
                                contentDescription = null)
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Gray,
                        modifier = Modifier.background(NavColor),
                        onClick = {
                            navController.navigate(bottomNavItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Navigation(
            modifier,
            navController,
            innerPadding,
            plants.categories,
            plants.purchaseInfos
        )
    }
}

@Composable
fun Navigation(
    modifier: Modifier,
    navController: NavHostController,
    innerPadding: PaddingValues,
    categories: List<Properties>,
    purchaseInfos: List<PurchaseInfo>,
) {
    NavHost(navController,
        startDestination = BottomNavItems.HomeScreen.route,
        modifier.padding(innerPadding)) {
        composable(
            BottomNavItems.HomeScreen.route
        ) {
            HomeTab(
                modifier = modifier,
                categories,
                purchaseInfos,
            ) { price, plantName, resourceId ->
                navController.navigate("$PURCHASE_ROUTE/$price/$plantName/$resourceId")
            }
        }
        composable(
            "$PURCHASE_ROUTE/{$PRICE}/{$PLANT_NAME}/{$RESOURCE_ID}",
            arguments = listOf(
                navArgument(PRICE) { type = NavType.LongType },
                navArgument(PLANT_NAME) { type = NavType.StringType },
                navArgument(RESOURCE_ID) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val arg = backStackEntry.arguments
            PurchaseScreen(
                modifier = modifier,
                arg?.getLong(PRICE)!!,
                arg.getString(PLANT_NAME)!!,
                arg.getInt(RESOURCE_ID)
            ) {
                navController.popBackStack()
            }
        }
        composable(BottomNavItems.FavoritesScreen.route) { OtherScreensVision(modifier, FAVORITES) }
        composable(BottomNavItems.OffersScreen.route) { OtherScreensVision(modifier, OFFERS) }
        composable(BottomNavItems.ShoppingScreen.route) { OtherScreensVision(modifier,SHOPPING_CART) }
    }
}

@Composable
fun OtherScreensVision(modifier: Modifier, name: String) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = name)
    }
}
