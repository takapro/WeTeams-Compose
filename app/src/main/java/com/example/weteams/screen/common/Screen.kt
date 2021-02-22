package com.example.weteams.screen.common

import com.example.weteams.R

fun getScreenGroups(currentProject: String?) =
    arrayOf(
        ScreenGroup(
            screens = arrayOf(Screen.PROJECTS)
        ),
        ScreenGroup(
            title = currentProject ?: "No Projects",
            enabled = currentProject != null,
            screens = arrayOf(Screen.DASHBOARD, Screen.SCHEDULE, Screen.FILES, Screen.CHAT)
        ),
        ScreenGroup(
            screens = arrayOf(Screen.SETTINGS)
        ),
    )

data class ScreenGroup(
    val title: String? = null,
    val enabled: Boolean = true,
    val screens: Array<Screen>,
)

enum class Screen(val title: String, val iconRes: Int) {
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
