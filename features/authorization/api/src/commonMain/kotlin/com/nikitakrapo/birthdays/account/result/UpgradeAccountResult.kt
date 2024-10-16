package com.nikitakrapo.birthdays.account.result

import com.nikitakrapo.birthdays.account.models.User

sealed interface UpgradeAccountResult {

    data class Success(
        val user: User,
    ) : UpgradeAccountResult

    data object AlreadyUpgraded : UpgradeAccountResult

    data object NotLoggedIn : UpgradeAccountResult

    data class UnknownError(
        val message: String?,
    ) : UpgradeAccountResult
}