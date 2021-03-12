package com.example.weteams.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainViewModel : ViewModel(), FirebaseAuth.AuthStateListener {
    val _user: MutableLiveData<FirebaseUser?>
    val user: LiveData<FirebaseUser?>
        get() = _user

    init {
        val auth = FirebaseAuth.getInstance()
        _user = MutableLiveData(auth.currentUser)
        auth.addAuthStateListener(this)
    }

    override fun onCleared() {
        super.onCleared()
        FirebaseAuth.getInstance().removeAuthStateListener(this)
    }

    override fun onAuthStateChanged(auth: FirebaseAuth) {
        _user.value = auth.currentUser
    }
}
