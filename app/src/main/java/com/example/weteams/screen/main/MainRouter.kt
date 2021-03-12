package com.example.weteams.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weteams.screen.Route
import com.example.weteams.screen.SubRoute
import com.example.weteams.screen.chat.ChatScreen
import com.example.weteams.screen.dashboard.DashboardScreen
import com.example.weteams.screen.files.FilesScreen
import com.example.weteams.screen.getRouteGroups
import com.example.weteams.screen.projects.ProjectsScreen
import com.example.weteams.screen.projects.ProjectsViewModel
import com.example.weteams.screen.schedule.ScheduleScreen
import com.example.weteams.screen.settings.SettingsScreen
import com.example.weteams.ui.common.DrawerContent
import com.example.weteams.ui.common.PrimaryBar
import com.example.weteams.ui.common.SecondaryBar

@Composable
fun MainRouter() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    val projectsViewModel = viewModel<ProjectsViewModel>()
    val currentProject = projectsViewModel.currentProject.observeAsState()

    fun NavGraphBuilder.route(
        route: Route,
        content: @Composable (NavBackStackEntry) -> Unit
    ) {
        composable(route.toString()) {
            Column {
                PrimaryBar(scaffoldState.drawerState, route.title)
                content(it)
            }
        }
    }

    fun NavGraphBuilder.subroute(
        subroute: SubRoute,
        content: @Composable (NavBackStackEntry) -> Unit
    ) {
        composable(subroute.toString()) {
            Column {
                SecondaryBar(navController, subroute.title)
                content(it)
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerContent(
                drawerState = scaffoldState.drawerState,
                navController = navController,
                routeGroups = getRouteGroups(currentProject.value?.name)
            )
        },
        drawerGesturesEnabled = false
    ) {
        NavHost(navController, startDestination = Route.PROJECTS.toString()) {
            route(Route.PROJECTS) { ProjectsScreen(projectsViewModel) }
            route(Route.DASHBOARD) { DashboardScreen() }
            route(Route.SCHEDULE) { ScheduleScreen() }
            route(Route.FILES) { FilesScreen() }
            route(Route.CHAT) { ChatScreen() }
            route(Route.SETTINGS) { SettingsScreen() }
        }
    }
}
