package com.jetbrains.handson.kmm.shared.di

import com.jetbrains.handson.kmm.shared.PropertiesRepository
import com.jetbrains.handson.kmm.shared.SpaceXRepository
import com.jetbrains.handson.kmm.shared.cache.AppDatabase
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class SharedComponent(
    @Component val platformModule: PlatformModule,
    @Component val networkModule: NetworkModule
) {

    abstract val spaceXRepository: SpaceXRepository
    abstract val propertiesRepository: PropertiesRepository

    @get:Provides
    val appDatabase = AppDatabase(platformModule.databaseDriverFactory.createDriver())

    companion object {
        fun create(): SharedComponent {
            return SharedComponent::class.create(
                PlatformModule::class.create(),
                NetworkModule::class.create()
            )
        }
    }
}