package com.example.final_project.data

import android.content.ContentValues
import android.util.Log
import com.example.final_project.model.Laptop
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface LaptopRepository {
    fun getAll(): Flow<List<Laptop>>
    suspend fun save(laptop: Laptop): String
    suspend fun update(laptop: Laptop)
    suspend fun delete(laptopId: String)
    fun getLaptopById(laptopId: String): Flow<Laptop>
}

class LaptopRepositoryImpl(private val firestore: FirebaseFirestore) : LaptopRepository {
    override fun getAll(): Flow<List<Laptop>> = flow {
        val snapshot = firestore.collection("Laptop")
            .orderBy("nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val laptop = snapshot.toObjects(Laptop::class.java)
        emit(laptop)
    }.flowOn(Dispatchers.IO)


    override suspend fun save(laptop: Laptop): String {
        return try {
            val documentReference = firestore.collection("Laptop").add(laptop).await()
            // Update the Laptop with the Firestore-generated DocumentReference
            firestore.collection("Laptop").document(documentReference.id)
                .set(laptop.copy(id = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(laptop: Laptop) {
        firestore.collection("Laptop").document(laptop.id).set(laptop).await()
    }

    override suspend fun delete(laptopId: String) {
        firestore.collection("Laptop").document(laptopId).delete().await()
    }

    override fun getLaptopById(laptopId: String): Flow<Laptop> {
        return flow {
            val snapshot = firestore.collection("Laptop").document(laptopId).get().await()
            val laptop = snapshot.toObject(Laptop::class.java)
            emit(laptop!!)
        }.flowOn(Dispatchers.IO)
    }

}
