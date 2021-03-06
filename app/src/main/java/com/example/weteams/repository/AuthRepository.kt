package com.example.weteams.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()

    suspend fun signIn(email: String, password: String): Boolean {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        return result != null
    }

    suspend fun signUp(email: String, password: String, displayName: String): Boolean {
        val result = auth.createUserWithEmailAndPassword(email, password).await()

        result.user?.also { user ->
            val request = UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                .build()
            user.updateProfile(request).await()

            val db = FirebaseFirestore.getInstance()
            val profile = mapOf("email" to email, "displayName" to displayName)
            db.collection("users").document(user.uid).set(profile).await()
        }

        return result != null
    }
}
