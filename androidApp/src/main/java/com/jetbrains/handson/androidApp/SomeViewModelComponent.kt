package com.jetbrains.handson.androidApp

import com.jetbrains.handson.kmm.shared.PropertiesRepository
import com.jetbrains.handson.kmm.shared.di.PlatformModule
import com.jetbrains.handson.kmm.shared.di.SharedComponent
import com.jetbrains.handson.kmm.shared.SpaceXRepository
import com.jetbrains.handson.kmm.shared.di.NetworkModule
import com.jetbrains.handson.kmm.shared.di.create
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class SomeViewModelComponent {

    @Provides
    fun provideSpaceXRepository(sharedComponent: SharedComponent): SpaceXRepository {
        return sharedComponent.spaceXRepository
    }

    @Provides
    fun providePropertiesRepository(sharedComponent: SharedComponent): PropertiesRepository {
        return sharedComponent.propertiesRepository
    }

    @Provides
    fun provideSharedComponent(): SharedComponent {
        return SharedComponent.create()
    }
}