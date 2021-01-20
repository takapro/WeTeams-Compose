package com.example.weteams.screen.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.example.weteams.screen.signin.SignInActivity
import com.example.weteams.ui.theme.WeTeamsTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }

        setContent {
            WeTeamsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(
                        name = user.displayName ?: "unknown user",
                        onSignOut = {
                            FirebaseAuth.getInstance().signOut()
                            startActivity(Intent(this, SignInActivity::class.java))
                            finish()
                        }
                    )
                }
            }
        }
    }
}
