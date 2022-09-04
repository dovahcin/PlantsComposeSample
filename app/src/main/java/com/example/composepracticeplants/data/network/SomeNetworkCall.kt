package com.example.composepracticeplants.data.network

import com.example.composepracticeplants.R
import com.example.composepracticeplants.domain.Properties
import com.example.composepracticeplants.domain.PurchaseInfo

object SomeNetworkCall {
    val purchaseInfo = mutableListOf(
        PurchaseInfo("Yucca", 80L, R.drawable.yuccaplant),
        PurchaseInfo("Shamdani", 75L, R.drawable.shamdani),
        PurchaseInfo("Anjiri", 120L, R.drawable.anjiri),
        PurchaseInfo("Ficus", 40L, R.drawable.bargpahn)
    )
    val categories = mutableListOf(
        Properties("Green Plants", R.drawable.green_plant),
        Properties("Desert Plants", R.drawable.desert_plant),
        Properties("Watering Plants", R.drawable.watering_plants),
        Properties("Vegetables", R.drawable.tomato),
    )
}