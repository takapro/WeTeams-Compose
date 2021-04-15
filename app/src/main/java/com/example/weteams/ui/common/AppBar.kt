package com.example.weteams.ui.common

import androidx.compose.material.DrawerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.weteams.R
import kotlinx.coroutines.launch

@Composable
fun PrimaryBar(drawerState: DrawerState, title: String) {
    val coroutineScope = rememberCoroutineScope()
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_menu_white_24dp),
                    contentDescription = ""
                )
            }
        }
    )
}

@Composable
fun SecondaryBar(navController: NavHostController, title: String) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back_white_24dp),
                    contentDescription = ""
                )
            }
        }
    )
}
