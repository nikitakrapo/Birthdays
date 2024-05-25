package com.nikitakrapo.trips.network

import com.nikitakrapo.birthdays.account.AccountManager

class AuthorizationTokenProviderImpl(
    private val accountManager: AccountManager,
) : AuthorizationTokenProvider {

    override suspend fun getToken(): String? {
        return accountManager.getToken()
    }
}