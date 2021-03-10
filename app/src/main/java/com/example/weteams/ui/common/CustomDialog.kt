package com.example.weteams.ui.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun CustomDialog(
    state: MutableState<Boolean>,
    title: String,
    onConfirm: () -> Unit,
    enableConfirm: Boolean = true,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = { state.value = false },
        title = { Text(title) },
        text = content,
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    state.value = false
                },
                enabled = enableConfirm
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(
                onClick = { state.value = false }
            ) {
                Text("Cancel")
            }
        }
    )
}
