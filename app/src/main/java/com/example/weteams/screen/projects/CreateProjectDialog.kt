package com.example.weteams.screen.projects

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.weteams.ui.common.CustomDialog
import com.example.weteams.ui.common.InputField

@Composable
fun CreateProjectDialog(
    dialogState: MutableState<Boolean>,
    projectsViewModel: ProjectsViewModel
) {
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    CustomDialog(
        state = dialogState,
        title = "Create Project",
        onConfirm = {
            projectsViewModel.createProject(name.value, description.value)
        },
        enableConfirm = name.value != "" && description.value != ""
    ) {
        Column {
            InputField("Project Name", name)
            InputField("Description", description)
        }
    }
}
