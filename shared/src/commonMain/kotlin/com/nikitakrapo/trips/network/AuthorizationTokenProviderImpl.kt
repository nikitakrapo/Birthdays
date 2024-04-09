package com.nikitakrapo.trips.network

import com.nikitakrapo.trips.account.AccountManager

class AuthorizationTokenProviderImpl(
    private val accountManager: AccountManager,
) : AuthorizationTokenProvider {

    override suspend fun getToken(): String? {
        return accountManager.getToken()
    }
}