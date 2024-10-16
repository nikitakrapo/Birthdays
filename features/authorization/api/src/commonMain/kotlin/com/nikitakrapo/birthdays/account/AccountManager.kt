package com.nikitakrapo.birthdays.account

import com.nikitakrapo.birthdays.account.models.User
import com.nikitakrapo.birthdays.account.result.LoginResult
import com.nikitakrapo.birthdays.account.result.RegistrationResult
import com.nikitakrapo.birthdays.account.result.UpgradeAccountResult
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

interface AccountManager {

    val user: StateFlow<User?>

    suspend fun registerAnonymously(): LoginResult

    /**
     * Upgrades anonymous account to full
     */
    suspend fun upgradeAccount(
        email: String,
        password: String,
    ): UpgradeAccountResult

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
