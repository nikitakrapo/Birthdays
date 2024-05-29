package com.nikitakrapo.birthdays.cms

import android.content.Context
import com.nikitakrapo.birthdays.platform.PlatformContext
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual internal fun pushTokenSettings(platformContext: PlatformContext): Settings =
    SharedPreferencesSettings(
        delegate = platformContext.applicationContext
            .getSharedPreferences("push_token", Context.MODE_PRIVATE),
    )