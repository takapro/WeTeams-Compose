package com.example.weteams.screen.signin

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun SignInForm(viewModel: SignInViewModel) {
    val signIn = savedInstanceState { true }
    val displayName = savedInstanceState { "" }
    val email = savedInstanceState { "" }
    val password = savedInstanceState { "" }
    val confirmPassword = savedInstanceState { "" }
    val isProcessing = viewModel.isProcessing.observeAsState(false)

    Box {
        ScrollableColumn(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "WeTeam",
                color = MaterialTheme.colors.primarySurface,
                fontSize = TextUnit.Sp(48)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TabRow(
                selectedTabIndex = if (signIn.value) 0 else 1,
                modifier = Modifier.width(240.dp),
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.primarySurface
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
                TextField(
                    value = displayName.value,
                    onValueChange = { displayName.value = it },
                    label = { Text("Username") },
                    singleLine = true,
                    backgroundColor = MaterialTheme.colors.background
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("E-mail") },
                singleLine = true,
                backgroundColor = MaterialTheme.colors.background
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                backgroundColor = MaterialTheme.colors.background
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (!signIn.value) {
                TextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    backgroundColor = MaterialTheme.colors.background
                )

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
                            displayName = displayName.value,
                            email = email.value,
                            password = password.value
                        )
                    }
                },
                enabled = !isProcessing.value && viewModel.canSignIn(
                    signIn = signIn.value,
                    displayName = displayName.value,
                    email = email.value,
                    password = password.value,
                    confirmPassword = confirmPassword.value
                )
            ) {
                Text(text = if (signIn.value) "Sign In" else "Sign Up")
            }
        }

        if (isProcessing.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = contentColorFor(MaterialTheme.colors.background)
                            .copy(alpha = 0.2F)
                    ),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
