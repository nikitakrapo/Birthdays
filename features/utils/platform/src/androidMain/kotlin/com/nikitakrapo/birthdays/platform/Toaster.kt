package com.nikitakrapo.birthdays.platform

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

actual fun showToast(context: PlatformContext, text: String) {
    Toast.makeText(context.applicationContext, text, LENGTH_SHORT).show()
}