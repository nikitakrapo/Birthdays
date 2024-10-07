package com.nikitakrapo.birthdays.android

import android.app.Application
import com.nikitakrapo.birthdays.di.AppDi
import com.nikitakrapo.birthdays.platform.PlatformContext
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class BirthdaysApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        AppDi.start(platformContext())
    }

    private fun platformContext() = PlatformContext(this)
}