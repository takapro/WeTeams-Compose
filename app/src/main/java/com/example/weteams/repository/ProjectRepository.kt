package com.example.weteams.repository

import com.example.weteams.entity.Project
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

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

    suspend fun createProject(user: FirebaseUser, name: String, description: String) {
        val db = FirebaseFirestore.getInstance()
        val project = mapOf(
            "name" to name,
            "description" to description,
            "members" to listOf(db.collection("users").document(user.uid))
        )
        db.collection("projects").add(project).await()
    }

    suspend fun joinProject(user: FirebaseUser, project: Project) {
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(user.uid)
        val projectRef = db.collection("projects").document(project.id)
        projectRef.update("members", FieldValue.arrayUnion(userRef)).await()
    }
}
