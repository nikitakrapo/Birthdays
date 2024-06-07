package com.nikitakrapo.birthdays.android

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.nikitakrapo.birthdays.platform.PlatformContext
import com.nikitakrapo.birthdays.di.AppDi
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class BirthdaysApplication : Application() {

    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        AppDi.start(platformContext())
        analytics = Firebase.analytics
    }

    private fun platformContext() = PlatformContext(this)
}