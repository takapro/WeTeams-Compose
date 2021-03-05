package com.example.weteams.screen.projects

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProjectsViewModel : ViewModel() {
    val currentProject = MutableLiveData<String?>()
}
