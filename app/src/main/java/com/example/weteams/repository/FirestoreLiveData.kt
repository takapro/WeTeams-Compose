package com.example.weteams.repository

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query

class FirestoreLiveData<T>(
    val classT: Class<T>,
    query: Query? = null
) : LiveData<List<T>>(emptyList()) {
    private var listenerRegistration: ListenerRegistration? = null

    init {
        if (query != null) {
            load(query)
        }
    }

    fun load(query: Query) {
        cleanup()

        listenerRegistration = query.addSnapshotListener { value, e ->
            if (value != null) {
                setValue(value.toObjects(classT))
            } else {
                e?.printStackTrace()
            }
        }
    }

    fun cleanup() {
        listenerRegistration?.remove()
        listenerRegistration = null
    }
}
