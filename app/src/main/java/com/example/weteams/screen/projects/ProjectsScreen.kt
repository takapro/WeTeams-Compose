package com.example.weteams.screen.projects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weteams.entity.Project

@Composable
fun ProjectsScreen(projectsViewModel: ProjectsViewModel) {
    val joinedProjects = projectsViewModel.joinedProjects.observeAsState(emptyList())
    val currentProject = projectsViewModel.currentProject.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
        ) {
            Button(
                modifier = Modifier.weight(1.0f),
                onClick = {
                    // TODO
                }
            ) { Text("Create Project") }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                modifier = Modifier.weight(1.0f),
                onClick = {
                    // TODO
                }
            ) { Text("Join Project") }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val project = currentProject.value
            if (project != null) {
                item {
                    CurrentProjectItem(
                        project = project,
                        onClick = {
                            projectsViewModel.setCurrentProject(null)
                        }
                    )
                }
            }

            val projects = joinedProjects.value.filter { it != project }
            items(projects.size) { index ->
                ProjectItem(
                    project = projects[index],
                    onClick = {
                        projectsViewModel.setCurrentProject(projects[index])
                    }
                )
            }
        }
    }
}

@Composable
fun CurrentProjectItem(project: Project, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(172.dp)
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Current Project"
        )
        Text(
            text = project.name,
            fontSize = 34.sp
        )
        Text(
            text = project.description,
            fontSize = 20.sp
        )
    }
}

@Composable
fun ProjectItem(project: Project, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp)
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = project.name,
            fontSize = 24.sp
        )
        Text(
            text = project.description
        )
    }
}
