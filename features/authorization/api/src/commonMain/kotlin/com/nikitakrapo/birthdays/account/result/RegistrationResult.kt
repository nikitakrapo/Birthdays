package com.nikitakrapo.birthdays.account.result

import com.nikitakrapo.birthdays.account.models.User

sealed interface RegistrationResult {

    data class Success(
        val user: User,
    ) : RegistrationResult

    data class Error(
        val message: String?,
        val type: RegistrationErrorType = RegistrationErrorType.UNKNOWN,
    ) : RegistrationResult
}