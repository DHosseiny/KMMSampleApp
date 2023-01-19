package com.jetbrains.handson.kmm.shared

import android.app.Application

abstract class BaseApp: Application() {

    override fun onCreate() {
        application = this
        super.onCreate()
    }
    companion object {
        lateinit var application: Application
    }
}