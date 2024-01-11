package com.example.final_project.tampilan

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class authviewmodel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Otentikasi sukses, Anda dapat menangani logika sesuai kebutuhan
                } else {
                    // Otentikasi gagal, tampilkan pesan kesalahan jika perlu
                }
            }
    }

    fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Otentikasi sukses, Anda dapat menangani logika sesuai kebutuhan
                } else {
                    // Otentikasi gagal, tampilkan pesan kesalahan jika perlu
                }
            }
    }

    // Metode lainnya sesuai kebutuhan, seperti pengiriman email verifikasi, cek status otentikasi, dll.

    // Misalnya, untuk mendapatkan pengguna saat ini:
    fun getCurrentUser(): FirebaseUser? = auth.currentUser
}