package com.example.weteams.screen.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weteams.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    private val user = FirebaseAuth.getInstance().currentUser

    private val _email = MutableLiveData(user.email)
    val email: LiveData<String>
        get() = _email

    private val _username = MutableLiveData(user.displayName)
    val username: LiveData<String>
        get() = _username

    private val _isProcessing = MutableLiveData(false)
    val isProcessing: LiveData<Boolean>
        get() = _isProcessing

    fun changeUsername(username: String) {
        _isProcessing.value = true

        viewModelScope.launch {
            try {
                authRepository.updateDisplayName(user, user.email!!, username)
                _username.value = user.displayName
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isProcessing.value = false
            }
        }
    }

    fun changePassword(currentPassword: String, newPassword: String) {
        _isProcessing.value = true

        viewModelScope.launch {
            try {
                authRepository.updatePassword(user, currentPassword, newPassword)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isProcessing.value = false
            }
        }
    }

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }
}
