package com.example.onboardingscreen.di

import android.app.Application
import com.example.onboardingscreen.data.manager.LocalUserManagerImpl
import com.example.onboardingscreen.domain.manager.LocalUserManager
import com.example.onboardingscreen.domain.useCases.AppEntryUseCases
import com.example.onboardingscreen.domain.useCases.ReadAppEntry
import com.example.onboardingscreen.domain.useCases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) : AppEntryUseCases = AppEntryUseCases(
        saveAppEntry = SaveAppEntry(localUserManager),
        readAppEntry = ReadAppEntry(localUserManager)
    )

}