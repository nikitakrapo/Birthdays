package com.nikitakrapo.birthdays.platform

import com.russhwolf.settings.Settings

expect fun createSettings(
    name: String,
    platformContext: PlatformContext,
): Settings