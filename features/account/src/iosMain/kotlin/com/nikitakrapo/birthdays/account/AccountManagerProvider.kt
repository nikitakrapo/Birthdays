package com.nikitakrapo.birthdays.account

actual fun AccountManager(): AccountManager = FirebaseAccountManager()