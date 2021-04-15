package com.example.weteams.screen.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weteams.repository.AuthRepository
import com.example.weteams.ui.common.ProcessingViewModel
import com.google.firebase.auth.FirebaseAuth

class SettingsViewModel : ProcessingViewModel() {
    private val authRepository = AuthRepository()
    private val user = FirebaseAuth.getInstance().currentUser

    private val _email = MutableLiveData(user?.email ?: "no email")
    val email: LiveData<String>
        get() = _email

    private val _username = MutableLiveData(user?.displayName ?: "unknown")
    val username: LiveData<String>
        get() = _username

    fun changeUsername(username: String) = process {
        authRepository.updateDisplayName(user, user?.email!!, username)
        _username.value = user.displayName
    }

    fun changePassword(currentPassword: String, newPassword: String) = process {
        authRepository.updatePassword(user, currentPassword, newPassword)
    }

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }
}
