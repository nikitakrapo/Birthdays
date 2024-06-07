package com.nikitakrapo.birthdays.network

import com.nikitakrapo.birthdays.account.AccountManager

class AuthorizationTokenProviderImpl(
    private val accountManager: AccountManager,
) : AuthorizationTokenProvider {

    override suspend fun getToken(): String? {
        return accountManager.getToken()
    }
}