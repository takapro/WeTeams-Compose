package com.example.weteams.repository

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    suspend fun signIn(email: String, password: String): Boolean {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        return result != null
    }

    suspend fun signUp(email: String, password: String, displayName: String): Boolean {
        val result = auth.createUserWithEmailAndPassword(email, password).await()

        result.user?.also { user ->
            updateDisplayName(user, email, password)
        }

        return result != null
    }

    suspend fun updateDisplayName(user: FirebaseUser, email: String, displayName: String) {
        val request = UserProfileChangeRequest.Builder()
            .setDisplayName(displayName)
            .build()
        user.updateProfile(request).await()

        val profile = mapOf("email" to email, "displayName" to displayName)
        db.collection("users").document(user.uid).set(profile).await()
    }

    suspend fun updatePassword(user: FirebaseUser, currentPassword: String, newPassword: String) {
        val credential = EmailAuthProvider.getCredential(user.email!!, currentPassword)
        user.reauthenticate(credential).await()

        user.updatePassword(newPassword).await()
    }
}
