package com.example.weteams.screen.settings

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.weteams.ui.common.PasswordField
import com.example.weteams.ui.common.ProcessingColumn

@Composable
fun SettingsPasswordContent(navController: NavHostController) {
    val settingsViewModel = viewModel<SettingsViewModel>()
    val currentPassword = rememberSaveable { mutableStateOf("") }
    val newPassword = rememberSaveable { mutableStateOf("") }
    val confirmPassword = rememberSaveable { mutableStateOf("") }
    val isProcessing = settingsViewModel.isProcessing.observeAsState(false)

    ProcessingColumn(isProcessing) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Change Password",
            color = MaterialTheme.colors.primary,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        PasswordField("Current Password", currentPassword)

        Spacer(modifier = Modifier.height(8.dp))

        PasswordField("New Password", newPassword)

        Spacer(modifier = Modifier.height(8.dp))

        PasswordField("Confirm Password", confirmPassword)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                settingsViewModel.changePassword(
                    currentPassword = currentPassword.value,
                    newPassword = newPassword.value,
                    onSuccess = { navController.navigateUp() }
                )
            },
            enabled = !isProcessing.value && currentPassword.value != "" &&
                newPassword.value != "" && newPassword.value == confirmPassword.value
        ) {
            Text("Change Username")
        }
    }
}
