package com.example.weteams.screen.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weteams.ui.common.CustomDialog

@Composable
fun SettingsContent() {
    val settingsViewModel = viewModel<SettingsViewModel>()
    val user = settingsViewModel.user

    val showSignOutDialog = remember { mutableStateOf(false) }

    val itemList = arrayOf(
        Pair(user?.email ?: "no email", null),
        Pair("Change Username", null),
        Pair("Change Password", null),
        Pair("Sign Out", { showSignOutDialog.value = true }),
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = user?.displayName ?: "unknown",
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

    if (showSignOutDialog.value) {
        CustomDialog(
            state = showSignOutDialog,
            title = "Sign Out",
            onConfirm = {
                settingsViewModel.signOut()
                showSignOutDialog.value = false
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
