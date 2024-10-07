package com.nikitakrapo.birthdays.account.result

import com.nikitakrapo.birthdays.account.models.User

sealed interface LoginResult {

    data class Success(
        val user: User,
    ) : LoginResult

    data class UnknownError(
        val error: String?,
    ) : LoginResult
}