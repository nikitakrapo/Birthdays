package com.nikitakrapo.trips.account

import kotlinx.coroutines.flow.StateFlow

interface AccountManager {

    val account: StateFlow<Account?>

    suspend fun login(
        email: String,
        password: String,
    ): LoginResult

    suspend fun register(
        email: String,
        password: String,
    ): RegistrationResult

    suspend fun logout()

    suspend fun getToken(): String?
}