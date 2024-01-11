package com.example.final_project.tampilan

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class authviewmodel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                } else {
                }
            }
    }


}