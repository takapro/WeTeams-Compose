package com.example.weteams.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawerLayout
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    fun NavGraphBuilder.route(
        screen: Screen,
        content: @Composable (NavBackStackEntry) -> Unit
    ) {
        composable(screen.toString()) {
            Column {
                TopBar(drawerState, screen.title)
                content(it)
            }
        }
    }

    ModalDrawerLayout(
        modifier = Modifier.fillMaxSize(),
        drawerState = drawerState,
        drawerContent = { DrawerContent(drawerState, navController) }
    ) {
        NavHost(navController, startDestination = Screen.PROJECTS.toString()) {
            route(Screen.PROJECTS) { ProjectsContent() }
            route(Screen.DASHBOARD) { DashboardContent() }
            route(Screen.SCHEDULE) { ScheduleContent() }
            route(Screen.FILES) { FilesContent() }
            route(Screen.CHAT) { ChatContent() }
            route(Screen.SETTINGS) { SettingsContent(viewModel::signOut) }
        }
    }
}

@Composable
fun TopBar(drawerState: DrawerState, title: String) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { drawerState.open() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_menu_white_24dp),
                    contentDescription = ""
                )
            }
        }
    )
}
