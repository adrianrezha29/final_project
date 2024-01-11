package com.example.final_project.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.data.LaptopRepository
import com.example.final_project.model.Laptop
import com.example.final_project.ui.HomeUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class LaptopUIState {
    data class Success(val kontak: Flow<List<Laptop>>) : LaptopUIState()
    object Error : LaptopUIState()
    object Loading : LaptopUIState()
}

class HomeViewModel(private val laptopRepository: LaptopRepository) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUIState: StateFlow<HomeUIState> = laptopRepository.getAll()
        .filterNotNull()
        .map {
            HomeUIState (listLaptop = it.toList(), it.size ) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState()
        )
}