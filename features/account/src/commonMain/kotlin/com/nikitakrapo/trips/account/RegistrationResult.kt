package com.nikitakrapo.trips.account

sealed interface RegistrationResult {

    data class Success(
        val account: Account,
    ) : RegistrationResult

    data class UnknownError(
        val error: Exception,
    ) : RegistrationResult
}