package com.example.onboardingscreen.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.onboardingscreen.presentation.onboarding.OnBoardingScreen
import com.example.onboardingscreen.presentation.onboarding.viewModel.OnboardingViewModel
import com.example.onboardingscreen.presentation.navigation.screenNames.Screens

@Composable
fun NavGraph(
    startDestination: Screens
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation<Screens.AppStartNavigation>(
            startDestination = Screens.OnboardingScreen
        ){
            composable<Screens.OnboardingScreen>{
                // Initializing the view model :->
                val viewModel = hiltViewModel<OnboardingViewModel>()

                // Calling Onboarding Screen :->
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }

        navigation<Screens.HomeNavigator>(
            startDestination = Screens.HomeScreen
        ){
            composable<Screens.HomeScreen>{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Home Screen",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }
        }
    }
}