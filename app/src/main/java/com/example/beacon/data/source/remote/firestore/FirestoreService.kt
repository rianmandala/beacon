package com.example.beacon.data.source.remote.firestore

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FirestoreService {

    private val citizensCollectionRef = Firebase.firestore.collection("citizens")

    fun getCitizenByNik(nik: String) = citizensCollectionRef
                .whereEqualTo("nik",nik)
                .get()
}