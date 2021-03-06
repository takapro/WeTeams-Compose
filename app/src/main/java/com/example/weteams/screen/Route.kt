package com.example.weteams.screen

import com.example.weteams.R

fun getRouteGroups(currentProject: String?) =
    arrayOf(
        RouteGroup(
            routes = arrayOf(Route.PROJECTS)
        ),
        RouteGroup(
            title = currentProject ?: "No Projects",
            enabled = currentProject != null,
            routes = arrayOf(Route.DASHBOARD, Route.SCHEDULE, Route.FILES, Route.CHAT)
        ),
        RouteGroup(
            routes = arrayOf(Route.SETTINGS)
        ),
    )

data class RouteGroup(
    val title: String? = null,
    val enabled: Boolean = true,
    val routes: Array<Route>,
)

enum class Route(val title: String, val iconRes: Int) {
    PROJECTS(
        title = "Projects",
        iconRes = R.drawable.ic_projects_black_24dp
    ),
    DASHBOARD(
        title = "Dashboard",
        iconRes = R.drawable.ic_projects_black_24dp
    ),
    SCHEDULE(
        title = "Schedule",
        iconRes = R.drawable.ic_projects_black_24dp
    ),
    FILES(
        title = "Files",
        iconRes = R.drawable.ic_projects_black_24dp
    ),
    CHAT(
        title = "Chat",
        iconRes = R.drawable.ic_projects_black_24dp
    ),
    SETTINGS(
        title = "Settings",
        iconRes = R.drawable.ic_projects_black_24dp
    );
}
