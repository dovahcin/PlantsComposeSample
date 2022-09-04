package com.example.composepracticeplants.data

import com.example.composepracticeplants.data.network.SomeNetworkCall.categories
import com.example.composepracticeplants.data.network.SomeNetworkCall.purchaseInfo
import com.example.composepracticeplants.domain.Plants
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PlantsRepository {
    suspend fun getPlantSpecs() = flow {
        emit(Plants(
            categories,
            purchaseInfo
        ))
    }.flowOn(IO)
}