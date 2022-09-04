package com.example.composepracticeplants.data

import com.example.composepracticeplants.data.network.SomeNetworkCall.purchaseInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PurchaseRepository {
    suspend fun getPurchaseInfo() = flow {
        emit(
            purchaseInfo
        )
    }.flowOn(Dispatchers.IO)
}