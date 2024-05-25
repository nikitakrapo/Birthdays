package com.nikitakrapo.birthdays.account

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

sealed interface LoginResult {

    data class Success(
        val account: Account,
    ) : LoginResult

    data class UnknownError(
        val error: String,
    ) : LoginResult
}

sealed interface RegistrationResult {

    data class Success(
        val account: Account,
    ) : RegistrationResult

    data class UnknownError(
        val error: String,
    ) : RegistrationResult
}