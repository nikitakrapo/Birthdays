package com.nikitakrapo.birthdays.account.models

import com.nikitakrapo.birthdays.account.Account

sealed interface RegistrationResult {

    data class Success(
        val account: Account,
    ) : RegistrationResult

    data class Error(
        val message: String,
        val type: RegistrationErrorType,
    ) : RegistrationResult
}