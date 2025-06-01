package com.nikitakrapo.birthdays.platform

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual fun createSettings(
    name: String,
    platformContext: PlatformContext,
): Settings = NSUserDefaultsSettings(delegate = NSUserDefaults())