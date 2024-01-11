package com.example.final_project.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.final_project.data.LaptopRepository
import com.example.final_project.ui.AddEvent
import com.example.final_project.ui.AddUIState
import com.example.final_project.ui.toLaptop

class AddViewModel(private val laptopRepository: LaptopRepository) : ViewModel() {

    var addUIState by mutableStateOf(AddUIState())
        private set

    fun updateAddUIState(addEvent: AddEvent) {
        addUIState = AddUIState(addEvent = addEvent)
    }

    suspend fun addLaptop() {
        laptopRepository.save(addUIState.addEvent.toLaptop())
    }
}