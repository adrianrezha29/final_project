package com.example.final_project.data

import com.example.final_project.model.Laptop
import kotlinx.coroutines.flow.Flow

interface LaptopRepository {
    fun getAll(): Flow<List<Laptop>>
    suspend fun save(laptop: Laptop): String
    suspend fun update(laptop: Laptop)
    suspend fun delete(laptopId: String)
    fun getLaptopById(laptopId: String): Flow<Laptop>
}