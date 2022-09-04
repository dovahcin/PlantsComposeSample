package com.example.composepracticeplants.domain

data class Plants(
    val categories: List<Properties> = listOf(),
    val purchaseInfos: List<PurchaseInfo> = listOf()
)