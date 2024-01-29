package com.nikitakrapo.trips.account

sealed interface LoginResult {

    data class Success(
        val account: Account,
    ) : LoginResult

    data class UnknownError(
        val error: Exception,
    ) : LoginResult
}