package com.jetbrains.handson.kmm.shared.di

import android.content.Context
import com.jetbrains.handson.kmm.shared.BaseApp
import com.jetbrains.handson.kmm.shared.cache.DatabaseDriverFactory
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
actual abstract class PlatformModule {

    @get:Provides
    val context: Context = BaseApp.application

    @get:Provides
    actual val databaseDriverFactory = DatabaseDriverFactory(context)

    actual companion object
}