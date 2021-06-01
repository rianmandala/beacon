package com.example.beacon.di

import com.example.beacon.data.source.SocialAssistanceRepository

object Injection {
    fun provideSocialAssistanceRepository(): SocialAssistanceRepository {
        return SocialAssistanceRepository.getInstance()
    }
}