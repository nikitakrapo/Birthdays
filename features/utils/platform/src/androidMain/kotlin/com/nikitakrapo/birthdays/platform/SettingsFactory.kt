package com.nikitakrapo.birthdays.platform

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual fun createSettings(
    name: String,
    platformContext: PlatformContext,
): Settings =
    SharedPreferencesSettings(
        delegate = platformContext.applicationContext
            .getSharedPreferences(name, Context.MODE_PRIVATE),
    )