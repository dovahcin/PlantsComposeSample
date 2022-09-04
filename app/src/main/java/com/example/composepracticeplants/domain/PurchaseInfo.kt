package com.example.composepracticeplants.domain

data class PurchaseInfo(
    val name: String = "",
    val price: Long = -1L,
    val resourceId: Int = -1,
    val isFavorable: Boolean = false
)
