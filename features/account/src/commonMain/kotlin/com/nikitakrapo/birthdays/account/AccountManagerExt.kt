package com.nikitakrapo.birthdays.account

val AccountManager.isAuthorized get() = account.value != null
