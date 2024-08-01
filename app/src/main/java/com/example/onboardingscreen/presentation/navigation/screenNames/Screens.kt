package com.example.onboardingscreen.presentation.navigation.screenNames

import kotlinx.serialization.Serializable

@Serializable object HomeScreen
@Serializable object OnboardingScreen

sealed class Screens {

    @Serializable data object AppStartNavigation : Screens()
    @Serializable data object HomeNavigator : Screens()

}