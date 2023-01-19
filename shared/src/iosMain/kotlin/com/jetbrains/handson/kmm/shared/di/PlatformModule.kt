package com.jetbrains.handson.kmm.shared.di

import com.jetbrains.handson.kmm.shared.cache.DatabaseDriverFactory
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
actual abstract class PlatformModule {

    actual val databaseDriverFactory: DatabaseDriverFactory
        @Provides get() = DatabaseDriverFactory()

    actual companion object
}