package com.nikitakrapo.trips.account

val AccountManager.isAuthorized get() = account.value != null
