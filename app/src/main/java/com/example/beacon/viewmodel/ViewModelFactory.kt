package com.example.beacon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beacon.data.source.SocialAssistanceRepository
import com.example.beacon.di.Injection
import com.example.beacon.ui.home.socialassistance.SocialAssistanceCheckViewModel

class ViewModelFactory private constructor(private val socialAssistanceRepository: SocialAssistanceRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
                instance ?: synchronized(this) {
                    ViewModelFactory(Injection.provideSocialAssistanceRepository()).apply {
                        instance = this
                    }
                }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SocialAssistanceCheckViewModel::class.java) -> {
                SocialAssistanceCheckViewModel(socialAssistanceRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
