package com.example.final_project

import android.app.Application
import com.example.final_project.data.AppContainer
import com.example.final_project.data.LaptopContainer

class LaptopAplication : Application(){
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = LaptopContainer()
    }
}