package com.example.weteams.screen.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weteams.entity.Project
import com.example.weteams.repository.FirestoreLiveData
import com.example.weteams.repository.ProjectRepository
import com.example.weteams.ui.common.ProcessingViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

class ProjectsViewModel : ProcessingViewModel() {
    private val user = FirebaseAuth.getInstance().currentUser

    val joinedProjects: FirestoreLiveData<Project>

    val _currentProject = MutableLiveData<Project?>()
    val currentProject: LiveData<Project?>
        get() = _currentProject

    init {
        joinedProjects = ProjectRepository.projectList(user)
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
        delay(3000) // TODO
    }

    fun joinProject(project: Project) = process {
        delay(3000) // TODO
    }
}
