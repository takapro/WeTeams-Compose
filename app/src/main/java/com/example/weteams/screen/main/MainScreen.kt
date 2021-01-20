package com.example.weteams.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.weteams.R

@Composable
fun MainScreen(name: String, onSignOut: () -> Unit) {
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(DrawerValue.Closed)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = { TopBar(scaffoldState) },
        drawerContent = { DrawerContent() }
    ) {
        MainContent(name = name, onSignOut = onSignOut)
    }
}

@Composable
fun TopBar(scaffoldState: ScaffoldState) {
    val title = stringResource(id = R.string.app_name)
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { scaffoldState.drawerState.open() }) {
                Icon(vectorResource(R.drawable.ic_menu_white_24dp))
            }
        }
    )
}
