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

enum class Screen {
    PROJECTS {
        override val text = "Projects"
        override val imageRes = R.drawable.ic_projects_black_24dp
    },
    DASHBOARD {
        override val text = "Dashboard"
        override val imageRes = R.drawable.ic_projects_black_24dp
    },
    SCHEDULE {
        override val text = "Schedule"
        override val imageRes = R.drawable.ic_projects_black_24dp
    },
    FILES {
        override val text = "Files"
        override val imageRes = R.drawable.ic_projects_black_24dp
    },
    CHAT {
        override val text = "Chat"
        override val imageRes = R.drawable.ic_projects_black_24dp
    },
    SETTINGS {
        override val text = "Settings"
        override val imageRes = R.drawable.ic_projects_black_24dp
    };

    abstract val text: String
    abstract val imageRes: Int
}
