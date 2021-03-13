package com.example.weteams.screen.signin

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weteams.ui.common.InputField
import com.example.weteams.ui.common.PasswordField
import com.example.weteams.ui.common.ProcessingColumn

@Composable
fun SignInForm(viewModel: SignInViewModel) {
    val signIn = rememberSaveable { mutableStateOf(true) }
    val username = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val confirmPassword = rememberSaveable { mutableStateOf("") }
    val isProcessing = viewModel.isProcessing.observeAsState(false)

    ProcessingColumn(
        isProcessing = isProcessing,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(state = rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "WeTeam",
            color = MaterialTheme.colors.primary,
            fontSize = 48.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        TabRow(
            selectedTabIndex = if (signIn.value) 0 else 1,
            modifier = Modifier.width(240.dp),
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.primary
        ) {
            Tab(
                selected = signIn.value,
                onClick = { signIn.value = true },
                text = { Text(text = "Sign In") }
            )
            Tab(
                selected = !signIn.value,
                onClick = { signIn.value = false },
                text = { Text(text = "Sign Up") }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (!signIn.value) {
            InputField("Username", username)

            Spacer(modifier = Modifier.height(8.dp))
        }

        InputField("E-mail", email)

        Spacer(modifier = Modifier.height(8.dp))

        PasswordField("Password", password)

        Spacer(modifier = Modifier.height(8.dp))

        if (!signIn.value) {
            PasswordField("Confirm Password", confirmPassword)

            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (signIn.value) {
                    viewModel.signIn(
                        email = email.value,
                        password = password.value
                    )
                } else {
                    viewModel.signUp(
                        username = username.value,
                        email = email.value,
                        password = password.value
                    )
                }
            },
            enabled = !isProcessing.value && viewModel.canSignIn(
                signIn = signIn.value,
                username = username.value,
                email = email.value,
                password = password.value,
                confirmPassword = confirmPassword.value
            )
        ) {
            Text(text = if (signIn.value) "Sign In" else "Sign Up")
        }
    }
}
