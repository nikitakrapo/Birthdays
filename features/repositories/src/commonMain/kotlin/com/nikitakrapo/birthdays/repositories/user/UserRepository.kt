package com.nikitakrapo.birthdays.repositories.user

import com.nikitakrapo.birthdays.network.result.NetworkResult
import kotlinx.datetime.LocalDate

interface UserRepository {

    suspend fun getUser(): NetworkResult<UserInfo>

    suspend fun createUser(
        displayName: String,
        birthdayDate: LocalDate,
    ): NetworkResult<UserInfo>
}