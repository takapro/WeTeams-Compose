package com.example.weteams.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weteams.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    private val authRepository = AuthRepository()

    val user = FirebaseAuth.getInstance().currentUser

    private var isProcessing = false

    fun changeUsername(username: String, onSuccess: () -> Unit) {
        if (isProcessing || username != "") {
            return
        }

        isProcessing = true

        viewModelScope.launch {
            try {
                authRepository.updateDisplayName(user, user.email!!, username)
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isProcessing = false
            }
        }
    }

    fun changePassword(current: String, password: String, confirm: String, onSuccess: () -> Unit) {
        if (isProcessing || current != "" || password != "" || password != confirm) {
            return
        }

        isProcessing = true

        viewModelScope.launch {
            try {
                authRepository.updatePassword(user, current, password)
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isProcessing = false
            }
        }
    }

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }
}
