package com.example.weteams.scene.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignInViewModel : ViewModel() {
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
                val auth = FirebaseAuth.getInstance()
                val result = auth.signInWithEmailAndPassword(email, password).await()
                _isSuccessful.value = result.user != null
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
                val auth = FirebaseAuth.getInstance()
                val result = auth.createUserWithEmailAndPassword(email, password).await()

                result.user?.also { user ->
                    val request = UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .build()
                    user.updateProfile(request).await()

                    val db = FirebaseFirestore.getInstance()
                    val profile = mapOf("email" to email, "displayName" to displayName)
                    db.collection("users").document(user.uid).set(profile).await()

                    _isSuccessful.value = true
                }
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
