package com.example.weteams.screen.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weteams.ui.common.CustomDialog
import com.example.weteams.ui.common.InputField

@Composable
fun SettingsUsernameDialog(
    dialogState: MutableState<Boolean>,
    initialUsername: String
) {
    val settingsViewModel = viewModel<SettingsViewModel>()
    val username = remember { mutableStateOf(initialUsername) }

    CustomDialog(
        state = dialogState,
        title = "Change Username",
        onConfirm = {
            settingsViewModel.changeUsername(username.value)
        },
        enableConfirm = username.value != ""
    ) {
        InputField("Username", username)
    }
}
