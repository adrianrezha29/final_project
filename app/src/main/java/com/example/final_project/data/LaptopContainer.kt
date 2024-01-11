package com.example.final_project.data

import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val laptopRepository: LaptopRepository
}

class LaptopContainer : AppContainer{
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val laptopRepository: LaptopRepository by lazy {
        LaptopRepositoryImpl(firestore)
    }
}