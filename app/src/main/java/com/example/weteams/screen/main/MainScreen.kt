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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.weteams.R
import com.example.weteams.screen.chat.ChatContent
import com.example.weteams.screen.common.DrawerContent
import com.example.weteams.screen.common.Screen
import com.example.weteams.screen.dashboard.DashboardContent
import com.example.weteams.screen.files.FilesContent
import com.example.weteams.screen.projects.ProjectsContent
import com.example.weteams.screen.schedule.ScheduleContent
import com.example.weteams.screen.settings.SettingsContent

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(DrawerValue.Closed)
    )

    val currentScreen by viewModel.currentScreen.observeAsState(Screen.PROJECTS)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = { TopBar(scaffoldState, title = currentScreen.text) },
        drawerContent = { DrawerContent(scaffoldState, viewModel) }
    ) {
        when (currentScreen) {
            Screen.PROJECTS -> ProjectsContent()
            Screen.DASHBOARD -> DashboardContent()
            Screen.SCHEDULE -> ScheduleContent()
            Screen.FILES -> FilesContent()
            Screen.CHAT -> ChatContent()
            Screen.SETTINGS -> SettingsContent(signOut = viewModel::signOut)
        }
    }
}

@Composable
fun TopBar(scaffoldState: ScaffoldState, title: String) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { scaffoldState.drawerState.open() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_menu_white_24dp),
                    contentDescription = ""
                )
            }
        }
    )
}
