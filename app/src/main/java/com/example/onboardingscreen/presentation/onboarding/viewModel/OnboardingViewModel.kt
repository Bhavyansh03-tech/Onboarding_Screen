package com.example.onboardingscreen.presentation.onboarding.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboardingscreen.domain.useCases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    fun onEvent(event: OnBoardingEvents){
        when(event){
            is OnBoardingEvents.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry(){
        viewModelScope.launch {
            Log.d("OnboardingViewModel", "Saving app entry status")
            appEntryUseCases.saveAppEntry()
        }
    }

}