package com.nikitakrapo.birthdays.account

val AccountManager.isAuthorized get() = account.value != null

fun AccountManager.requireAccount() = requireNotNull(account.value) { "Required account was null" }
