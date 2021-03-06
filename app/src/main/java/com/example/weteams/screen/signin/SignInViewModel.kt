package com.example.weteams.screen.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weteams.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private val authRepository = AuthRepository()

    private val _isProcessing = MutableLiveData(false)
    val isProcessing: LiveData<Boolean>
        get() = _isProcessing

    private val _isSuccessful = MutableLiveData(false)
    val isSuccessful: LiveData<Boolean>
        get() = _isSuccessful

    val errorMessage = MutableLiveData<String>()

    fun canSignIn(
        signIn: Boolean,
        displayName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return if (signIn) {
            email != "" && password != ""
        } else {
            displayName != "" && email != "" && password != "" && confirmPassword == password
        }
    }

    fun signIn(email: String, password: String) {
        _isProcessing.value = true

        viewModelScope.launch {
            try {
                _isSuccessful.value = authRepository.signIn(email, password)
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.value = when (e) {
                    is FirebaseAuthInvalidCredentialsException -> "Incorrect email or password"
                    else -> "Failed to sign in. Please check internet connection"
                }
            } finally {
                _isProcessing.value = false
            }
        }
    }

    fun signUp(displayName: String, email: String, password: String) {
        _isProcessing.value = true

        viewModelScope.launch {
            try {
                _isSuccessful.value = authRepository.signUp(email, password, displayName)
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.value = when (e) {
                    is FirebaseAuthUserCollisionException -> "User already exists"
                    is FirebaseAuthWeakPasswordException -> "Password needs to be at least 6 characters"
                    is FirebaseAuthInvalidCredentialsException -> "Wrong email address format"
                    else -> "Failed to sign up. Please check internet connection"
                }
            } finally {
                _isProcessing.value = false
            }
        }
    }
}
