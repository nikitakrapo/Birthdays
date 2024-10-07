package com.nikitakrapo.birthdays.platform

import platform.UIKit.UIAlertController
import platform.UIKit.UIApplication

actual fun showToast(context: PlatformContext, text: String) {
    val alert = UIAlertController().apply {
        setMessage(text)
    }
    val viewController = UIApplication.sharedApplication.keyWindow?.rootViewController
    viewController?.presentViewController(alert, animated = true, completion = null)
}