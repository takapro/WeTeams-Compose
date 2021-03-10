package com.example.weteams.screen.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weteams.ui.common.CustomDialog
import com.example.weteams.ui.common.ProcessingLazyColumn

@Composable
fun SettingsScreen() {
    val settingsViewModel = viewModel<SettingsViewModel>()
    val email = settingsViewModel.email.observeAsState("no email")
    val username = settingsViewModel.username.observeAsState("unknown")
    val isProcessing = settingsViewModel.isProcessing.observeAsState(false)

    val showUsernameDialog = remember { mutableStateOf(false) }
    val showPasswordDialog = remember { mutableStateOf(false) }
    val showSignOutDialog = remember { mutableStateOf(false) }

    val itemList = arrayOf(
        Pair(email.value, null),
        Pair("Change Username", { showUsernameDialog.value = true }),
        Pair("Change Password", { showPasswordDialog.value = true }),
        Pair("Sign Out", { showSignOutDialog.value = true }),
    )

    ProcessingLazyColumn(isProcessing) {
        item {
            Text(
                text = username.value,
                modifier = Modifier.padding(vertical = 64.dp),
                fontSize = 24.sp
            )
        }

        items(itemList.size) { index ->
            if (index > 0) {
                Divider()
            }

            val (text, onClick) = itemList[index]
            SettingsItem(text, onClick)
        }
    }

    if (showUsernameDialog.value) {
        SettingsUsernameDialog(showUsernameDialog, username.value)
    }

    if (showPasswordDialog.value) {
        SettingsPasswordDialog(showPasswordDialog)
    }

    if (showSignOutDialog.value) {
        CustomDialog(
            state = showSignOutDialog,
            title = "Sign Out",
            onConfirm = {
                settingsViewModel.signOut()
            }
        ) {
            Text("Do you want to sign out?")
        }
    }
}

@Composable
fun SettingsItem(text: String, onClick: (() -> Unit)?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .let {
                if (onClick != null) {
                    it.clickable { onClick() }
                } else {
                    it
                }
            }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 20.sp
        )
    }
}
