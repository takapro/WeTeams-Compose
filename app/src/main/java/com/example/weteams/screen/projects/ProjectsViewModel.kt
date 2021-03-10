package com.example.weteams.screen.projects

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weteams.entity.Project
import com.example.weteams.repository.FirestoreLiveData
import com.google.firebase.firestore.FirebaseFirestore

class ProjectsViewModel : ViewModel() {
    val currentProject = MutableLiveData<String?>()

    val projectsLiveData: FirestoreLiveData<Project>

    init {
        val db = FirebaseFirestore.getInstance()
        projectsLiveData = FirestoreLiveData(
            classT = Project::class.java,
            query = db.collection("projects").orderBy("name")
        )
    }

    override fun onCleared() {
        super.onCleared()
        projectsLiveData.cleanup()
    }
}
