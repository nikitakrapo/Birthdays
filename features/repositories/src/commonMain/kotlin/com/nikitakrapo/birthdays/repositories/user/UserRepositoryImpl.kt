package com.nikitakrapo.birthdays.repositories.user

import com.nikitakrapo.birthdays.network.result.NetworkResult
import com.nikitakrapo.birthdays.network.result.map
import com.nikitakrapo.birthdays.remote.UserApi
import com.nikitakrapo.birthdays.remote.data.UserInfoCreationRequestDto
import com.nikitakrapo.birthdays.remote.data.UserInfoDto
import com.nikitakrapo.birthdays.utils.encodeAsISO
import com.nikitakrapo.birthdays.utils.parseLocalDateOrNull
import io.ktor.client.HttpClient
import kotlinx.datetime.LocalDate

class UserRepositoryImpl(
    private val httpClient: HttpClient,
) : UserRepository {

    private val userApi by lazy { UserApi(httpClient) }

    override suspend fun getUser(): NetworkResult<UserInfo> {
        return userApi.getUser().map { it.toUserInfo() }
    }

    override suspend fun createUser(
        displayName: String,
        birthdayDate: LocalDate,
    ): NetworkResult<UserInfo> {
        val date = birthdayDate.encodeAsISO()
            ?: return NetworkResult.Error(
                exception = IllegalArgumentException("Incorrect date: $birthdayDate")
            )
        val request = UserInfoCreationRequestDto(
            displayName = displayName,
            birthdayDate = date,
        )
        return userApi.createUser(request).map { it.toUserInfo() }
    }

    private fun UserInfoDto.toUserInfo(): UserInfo? {
        return UserInfo(
            uid = uid,
            displayName = displayName,
            birthdayDate = birthdayDate.parseLocalDateOrNull() ?: return null,
        )
    }
}