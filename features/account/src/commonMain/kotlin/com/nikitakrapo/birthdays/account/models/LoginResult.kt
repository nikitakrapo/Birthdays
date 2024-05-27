package com.nikitakrapo.birthdays.account.models

import com.nikitakrapo.birthdays.account.Account

sealed interface LoginResult {

    data class Success(
        val account: Account,
    ) : LoginResult

    data class UnknownError(
        val error: String,
    ) : LoginResult
}