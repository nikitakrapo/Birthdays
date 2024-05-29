package com.nikitakrapo.birthdays.platform

import android.annotation.SuppressLint
import android.provider.Settings.Secure

@SuppressLint("HardwareIds")
actual fun getDeviceId(context: PlatformContext): String? {
    return Secure.getString(context.applicationContext.contentResolver, Secure.ANDROID_ID)
}