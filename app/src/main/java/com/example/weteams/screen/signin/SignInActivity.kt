package com.example.weteams.screen.signin

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.example.weteams.MainActivity
import com.example.weteams.ui.theme.WeTeamsTheme

class SignInActivity : AppCompatActivity() {
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceBundle: Bundle?) {
        super.onCreate(savedInstanceBundle)

        viewModel.isSuccessful.observe(this) {
            if (it) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        viewModel.errorMessage.observe(this) {
            if (it != null) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                viewModel.errorMessage.value = null
            }
        }

        setContent {
            WeTeamsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SignInForm(viewModel)
                }
            }
        }
    }
}
