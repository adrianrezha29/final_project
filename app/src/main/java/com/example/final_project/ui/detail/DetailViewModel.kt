package com.example.final_project.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.data.LaptopRepository
import com.example.final_project.ui.DetailUIState
import com.example.final_project.ui.toDetailLaptop
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: LaptopRepository
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val laptopId: String = checkNotNull(savedStateHandle[DetailDestination.laptopId])

    val uiState: StateFlow<DetailUIState> =
        repository.getLaptopById(laptopId)
            .filterNotNull()
            .map {
                DetailUIState(addEvent = it.toDetailLaptop())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )

    suspend fun deleteLaptop() {
        repository.delete(laptopId)
    }
}