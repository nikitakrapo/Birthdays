package com.nikitakrapo.trips.account

import com.nikitakrapo.trips.account.firebase.FirebaseAccountManager

actual fun AccountManager(): AccountManager = FirebaseAccountManager()