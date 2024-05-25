package com.nikitakrapo.trips.account

import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

interface AccountManager {

    val account: StateFlow<Account?>

    suspend fun login(
        email: String,
        password: String,
    ): LoginResult

    suspend fun register(
        username: String,
        email: String,
        birthday: LocalDate,
        password: String,
    ): RegistrationResult

    suspend fun logout()

    suspend fun getToken(): String?
}