package com.example.weteams.scene.signin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.example.weteams.ui.theme.WeTeamsTheme

class SignInActivity : AppCompatActivity() {
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceBundle: Bundle?) {
        super.onCreate(savedInstanceBundle)

        setContent {
            WeTeamsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SignInForm(viewModel)
                }
            }
        }
    }
}
