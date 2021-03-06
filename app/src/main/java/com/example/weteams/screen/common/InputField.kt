package com.example.weteams.screen.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun InputField(
    label: String,
    value: MutableState<String>
) {
    TextField(
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text(text = label) },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background
        )
    )
}

@Composable
fun PasswordField(
    label: String,
    value: MutableState<String>
) {
    TextField(
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text(label) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background
        )
    )
}
