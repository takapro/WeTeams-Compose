package com.example.weteams.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.DrawerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weteams.R
import com.example.weteams.screen.Route
import com.example.weteams.screen.chat.ChatContent
import com.example.weteams.screen.dashboard.DashboardContent
import com.example.weteams.screen.files.FilesContent
import com.example.weteams.screen.projects.ProjectsContent
import com.example.weteams.screen.schedule.ScheduleContent
import com.example.weteams.screen.settings.SettingsContent
import com.example.weteams.ui.common.DrawerContent
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    fun NavGraphBuilder.route(
        route: Route,
        content: @Composable (NavBackStackEntry) -> Unit
    ) {
        composable(route.toString()) {
            Column {
                TopBar(scaffoldState.drawerState, route.title)
                content(it)
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { DrawerContent(scaffoldState.drawerState, navController) }
    ) {
        NavHost(navController, startDestination = Route.PROJECTS.toString()) {
            route(Route.PROJECTS) { ProjectsContent() }
            route(Route.DASHBOARD) { DashboardContent() }
            route(Route.SCHEDULE) { ScheduleContent() }
            route(Route.FILES) { FilesContent() }
            route(Route.CHAT) { ChatContent() }
            route(Route.SETTINGS) { SettingsContent() }
        }
    }
}

@Composable
fun TopBar(drawerState: DrawerState, title: String) {
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
