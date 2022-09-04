package com.example.composepracticeplants

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.composepracticeplants.domain.Properties
import com.example.composepracticeplants.domain.PurchaseInfo
import com.example.composepracticeplants.features.homeScreen.HomeTab
import com.example.composepracticeplants.features.homeScreen.HomeViewModel
import com.example.composepracticeplants.features.mainScreen.BottomNavItems
import com.example.composepracticeplants.features.mainScreen.MainScaffold
import com.example.composepracticeplants.ui.theme.ComposePracticePlantsTheme
import kotlinx.coroutines.launch

class PlantsActivity : ComponentActivity() {
    private val viewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchPlantsCategory()

        lifecycleScope.launch {
            viewModel.plants.collect { plants ->
                val items = listOf(
                    BottomNavItems.HomeScreen,
                    BottomNavItems.FavoritesScreen,
                    BottomNavItems.OffersScreen,
                    BottomNavItems.ShoppingScreen
                )
                setContent {
                    ComposePracticePlantsTheme {
                        val navController = rememberNavController()
                        MainScaffold(Modifier, navController, items, plants)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    val categories = mutableListOf(
        Properties("Green Plants", R.drawable.green_plant),
        Properties("Desert Plants", R.drawable.desert_plant),
        Properties("Watering Plants", R.drawable.watering_plants),
        Properties("Vegetables", R.drawable.tomato),
    )
    val purchaseInfos = mutableListOf(
        PurchaseInfo("Yucca", 80L, R.drawable.yuccaplant),
        PurchaseInfo("Shamdani", 75L, R.drawable.shamdani),
        PurchaseInfo("Anjiri", 120L, R.drawable.anjiri),
        PurchaseInfo("Barg Pahn", 40L, R.drawable.bargpahn)
    )
    ComposePracticePlantsTheme {
        HomeTab(modifier = Modifier, categories, purchaseInfos){ _, _, _ ->
        }
    }
}
