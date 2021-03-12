package com.example.weteams.screen.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weteams.entity.Project
import com.example.weteams.repository.FirestoreLiveData
import com.example.weteams.repository.ProjectRepository
import com.google.firebase.auth.FirebaseAuth

class ProjectsViewModel : ViewModel() {
    val joinedProjects: FirestoreLiveData<Project>

    val _currentProject = MutableLiveData<Project?>()
    val currentProject: LiveData<Project?>
        get() = _currentProject

    init {
        val user = FirebaseAuth.getInstance().currentUser!!
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
}
