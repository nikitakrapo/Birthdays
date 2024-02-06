package com.nikitakrapo.trips.android

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.nikitakrapo.trips.di.AppDi
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class TripsApplication : Application() {

    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        com.nikitakrapo.trips.di.AppDi.start()
        analytics = Firebase.analytics
    }
}