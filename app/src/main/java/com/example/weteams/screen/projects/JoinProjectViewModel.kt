package com.example.weteams.screen.projects

import androidx.lifecycle.ViewModel
import com.example.weteams.entity.Project
import com.example.weteams.repository.FirestoreLiveData
import com.example.weteams.repository.ProjectRepository

class JoinProjectViewModel : ViewModel() {
    val allProjects: FirestoreLiveData<Project>

    init {
        allProjects = ProjectRepository.allProjectList()
    }

    override fun onCleared() {
        super.onCleared()
        allProjects.cleanup()
    }
}
