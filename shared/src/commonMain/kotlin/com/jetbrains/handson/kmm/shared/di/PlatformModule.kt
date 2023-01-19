package com.jetbrains.handson.kmm.shared.di

import com.jetbrains.handson.kmm.shared.cache.DatabaseDriverFactory

expect abstract class PlatformModule {

    actual val databaseDriverFactory: DatabaseDriverFactory

    companion object
}