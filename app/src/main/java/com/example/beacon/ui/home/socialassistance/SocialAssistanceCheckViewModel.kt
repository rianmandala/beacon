package com.example.beacon.ui.home.socialassistance

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beacon.data.response.Citizens
import com.example.beacon.data.source.SocialAssistanceRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SocialAssistanceCheckViewModel(private val socialAssistanceRepository: SocialAssistanceRepository): ViewModel() {
    val citizens: MutableLiveData<Citizens?> = MutableLiveData()

    fun getCitizenByNik(nik: String) {
        viewModelScope.launch {
            try{
                val querySnapshot = socialAssistanceRepository.getCitizenByNik(nik).await()
                if(querySnapshot.size()>0){
                    citizens.postValue(querySnapshot.documents[0].toObject(Citizens::class.java))
                }
                else citizens.value =null
            }catch (e: Exception){
                Log.d("Error",e.message.toString())
            }
        }
    }


}