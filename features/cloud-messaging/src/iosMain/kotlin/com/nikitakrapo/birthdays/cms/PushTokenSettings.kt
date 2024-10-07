package com.nikitakrapo.birthdays.cms

import com.nikitakrapo.birthdays.platform.PlatformContext
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual internal fun pushTokenSettings(platformContext: PlatformContext): Settings =
    NSUserDefaultsSettings(delegate = NSUserDefaults())