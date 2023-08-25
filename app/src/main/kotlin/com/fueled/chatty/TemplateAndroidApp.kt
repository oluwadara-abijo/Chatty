package com.fueled.chatty

import android.app.Application
import com.fueled.chatty.logging.ReleaseTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant

@HiltAndroidApp
class TemplateAndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initLogging()
    }

    private fun initLogging() {
        val logTree = if (BuildConfig.DEBUG) DebugTree() else ReleaseTree()
        plant(logTree)
    }
}
