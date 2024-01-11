package com.example.final_project.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.data.LaptopRepository
import com.example.final_project.ui.AddEvent
import com.example.final_project.ui.AddUIState
import com.example.final_project.ui.toLaptop
import com.example.final_project.ui.toUIStateLaptop
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: LaptopRepository
) : ViewModel() {

    var laptopUiState by mutableStateOf(AddUIState())
        private set

    private val laptopId: String = checkNotNull(savedStateHandle[EditDestination.kontakId])

    init {
        viewModelScope.launch {
            laptopUiState =
                repository.getLaptopById(laptopId)
                    .filterNotNull()
                    .first()
                    .toUIStateLaptop()
        }
    }

    fun updateUIState(addEvent: AddEvent) {
        laptopUiState = laptopUiState.copy(addEvent = addEvent)
    }

    suspend fun updateLaptop() {
        repository.update(laptopUiState.addEvent.toLaptop())

    }
}