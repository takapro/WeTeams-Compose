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
import com.example.weteams.ui.common.InputField
import com.example.weteams.ui.common.ProcessingColumn

@Composable
fun SettingsUsernameContent(navController: NavHostController) {
    val settingsViewModel = viewModel<SettingsViewModel>()
    val username = rememberSaveable { mutableStateOf("") }
    val isProcessing = settingsViewModel.isProcessing.observeAsState(false)

    ProcessingColumn(isProcessing) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Change Username",
            color = MaterialTheme.colors.primary,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        InputField("Username", username)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                settingsViewModel.changeUsername(
                    username = username.value,
                    onSuccess = { navController.navigateUp() }
                )
            },
            enabled = !isProcessing.value && username.value != ""
        ) {
            Text("Change Username")
        }
    }
}
