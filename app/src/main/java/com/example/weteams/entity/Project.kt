package com.example.weteams.entity

import com.google.firebase.firestore.DocumentId

data class Project(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val description: String = "",
)
