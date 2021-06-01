package com.example.beacon.data.source

import com.example.beacon.data.source.remote.firestore.FirestoreService

class SocialAssistanceRepository {

    companion object {
        @Volatile
        private var INSTANCE: SocialAssistanceRepository? = null

        fun getInstance(): SocialAssistanceRepository {
            return INSTANCE ?: synchronized(this) {
                SocialAssistanceRepository().apply {
                    INSTANCE = this
                }
            }
        }
    }

    fun getCitizenByNik(nik: String)= FirestoreService.getCitizenByNik(nik)

}