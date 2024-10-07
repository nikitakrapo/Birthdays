package com.nikitakrapo.birthdays.platform

import platform.UIKit.UIDevice

actual fun getDeviceId(context: PlatformContext): String? {
    return UIDevice.currentDevice.identifierForVendor?.UUIDString
}