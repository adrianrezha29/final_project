package com.example.final_project.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.final_project.LaptopAplication
import com.example.final_project.ui.add.AddViewModel

fun CreationExtras.apkikasiLaptop(): LaptopAplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LaptopAplication)

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            AddViewModel(apkikasiLaptop().container.laptopRepository)
        }


    }
}