package com.example.weteams.screen.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.weteams.screen.signin.SignInActivity
import com.example.weteams.ui.theme.WeTeamsTheme

class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.user.observe(this) {
            if (it == null) {
                startSignInActivity()
            }
        }

        setContent {
            WeTeamsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }

    fun startSignInActivity() {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }
}
