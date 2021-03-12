package com.example.weteams.screen.projects

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weteams.entity.Project
import com.example.weteams.ui.common.CustomDialog

@Composable
fun JoinProjectDialog(
    dialogState: MutableState<Boolean>,
    projectsViewModel: ProjectsViewModel
) {
    val joinProjectViewModel = viewModel<JoinProjectViewModel>()
    val allProjects = joinProjectViewModel.allProjects.observeAsState(emptyList())

    val joinedProjects = projectsViewModel.joinedProjects.observeAsState(emptyList())
    val joinedProjectIds = joinedProjects.value.map { it.id }
    val projects = allProjects.value.filter { !joinedProjectIds.contains(it.id) }

    val selectedProject = remember { mutableStateOf<Project?>(null) }

    CustomDialog(
        state = dialogState,
        title = null,
        onConfirm = {
            val project = selectedProject.value
            if (project != null) {
                projectsViewModel.joinProject(project)
            }
        },
        enableConfirm = selectedProject.value != null
    ) {
        Column(
        ) {
            Text(
                text = "Join Project",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxHeight(0.5f)
            ) {
                items(projects.size) { index ->
                    JoinProjectItem(
                        project = projects[index],
                        isSelected = selectedProject.value == projects[index],
                        onClick = {
                            selectedProject.value = projects[index]
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun JoinProjectItem(project: Project, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .let {
                if (isSelected) {
                    it.background(MaterialTheme.colors.secondary)
                } else {
                    it
                }
            }
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = project.name,
            fontSize = 18.sp
        )
        Text(
            text = project.description
        )
    }
}
