package com.example.weteams.screen.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weteams.entity.Project
import com.example.weteams.repository.FirestoreLiveData
import com.example.weteams.repository.ProjectRepository
import com.example.weteams.ui.common.ProcessingViewModel
import com.google.firebase.auth.FirebaseAuth

class ProjectsViewModel : ProcessingViewModel() {
    private val user = FirebaseAuth.getInstance().currentUser

    val joinedProjects: FirestoreLiveData<Project>

    val _currentProject = MutableLiveData<Project?>()
    val currentProject: LiveData<Project?>
        get() = _currentProject

    init {
        joinedProjects = ProjectRepository.projectList(user)
        joinedProjects.observeForever { projects ->
            _currentProject.value = projects.find { it.id == currentProject.value?.id }
        }
    }

    override fun onCleared() {
        super.onCleared()
        joinedProjects.cleanup()
    }

    fun setCurrentProject(project: Project?) {
        if (project == null || joinedProjects.value?.contains(project) == true) {
            _currentProject.value = project
        }
    }

    fun createProject(name: String, description: String) = process {
        ProjectRepository.createProject(user, name, description)
    }

    fun joinProject(project: Project) = process {
        ProjectRepository.joinProject(user, project)
    }
}
