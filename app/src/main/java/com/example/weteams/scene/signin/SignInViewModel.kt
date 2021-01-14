package com.example.weteams.scene.signin

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    private val _isProcessing = MutableLiveData(false)
    val isProcessing = _isProcessing as LiveData<Boolean>

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
        Handler().postDelayed({ _isProcessing.value = false }, 1000)
    }

    fun signUp(displayName: String, email: String, password: String) {
        _isProcessing.value = true
        Handler().postDelayed({ _isProcessing.value = false }, 2000)
    }
}
