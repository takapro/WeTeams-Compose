package com.example.weteams.repository

import com.example.weteams.entity.Project
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

object ProjectRepository {

    fun allProjectList(): FirestoreLiveData<Project> {
        val db = FirebaseFirestore.getInstance()
        return FirestoreLiveData(
            classT = Project::class.java,
            query = db.collection("projects")
                .orderBy("name")
        )
    }

    fun projectList(user: FirebaseUser): FirestoreLiveData<Project> {
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(user.uid)
        return FirestoreLiveData(
            classT = Project::class.java,
            query = db.collection("projects")
                .whereArrayContains("members", userRef)
                .orderBy("name")
        )
    }
}
