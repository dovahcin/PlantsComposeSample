package com.example.composepracticeplants.features.purchaseScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepracticeplants.data.PurchaseRepository
import com.example.composepracticeplants.domain.PurchaseInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PurchaseViewModel : ViewModel() {
    private val repository = PurchaseRepository()
    private val _purchaseInfo: MutableStateFlow<List<PurchaseInfo>> = MutableStateFlow(mutableListOf())
    val purchaseInfo = _purchaseInfo as StateFlow<List<PurchaseInfo>>

    fun fetchPlantsCategory() {
        viewModelScope.launch() {
            repository.getPurchaseInfo().collect { infos ->
                _purchaseInfo.value = infos
            }
        }
    }
}