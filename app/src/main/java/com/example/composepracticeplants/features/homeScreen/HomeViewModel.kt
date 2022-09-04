package com.example.composepracticeplants.features.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepracticeplants.data.PlantsRepository
import com.example.composepracticeplants.domain.Plants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(){
    private val repository = PlantsRepository()
    private val _plants : MutableStateFlow<Plants> = MutableStateFlow(Plants())
    val plants = _plants as StateFlow<Plants>

    fun fetchPlantsCategory() {
        viewModelScope.launch() {
            repository.getPlantSpecs().collect { plants ->
                _plants.value = plants
            }
        }
    }
}