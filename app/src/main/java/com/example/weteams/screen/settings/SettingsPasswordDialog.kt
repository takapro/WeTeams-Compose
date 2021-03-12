package com.example.weteams.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weteams.ui.common.CustomDialog
import com.example.weteams.ui.common.PasswordField

@Composable
fun SettingsPasswordDialog(
    dialogState: MutableState<Boolean>
) {
    val settingsViewModel = viewModel<SettingsViewModel>()
    val currentPassword = rememberSaveable { mutableStateOf("") }
    val newPassword = rememberSaveable { mutableStateOf("") }
    val confirmPassword = rememberSaveable { mutableStateOf("") }

    CustomDialog(
        state = dialogState,
        title = "Change Password",
        onConfirm = {
            settingsViewModel.changePassword(
                currentPassword = currentPassword.value,
                newPassword = newPassword.value
            )
        },
        enableConfirm = currentPassword.value != "" &&
            newPassword.value != "" && newPassword.value == confirmPassword.value
    ) {
        Column {
            PasswordField("Current Password", currentPassword)
            PasswordField("New Password", newPassword)
            PasswordField("Confirm Password", confirmPassword)
        }
    }
}
