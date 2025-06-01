package com.nikitakrapo.birthdays.remote

import com.nikitakrapo.birthdays.network.result.NetworkResult
import com.nikitakrapo.birthdays.network.result.getNetworkResult
import com.nikitakrapo.birthdays.remote.data.UserInfoCreationRequestDto
import com.nikitakrapo.birthdays.remote.data.UserInfoDto
import com.nikitakrapo.birthdays.utils.coroutines.onIo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal class UserApi(
    private val httpClient: HttpClient,
) {

    suspend fun getUser(): NetworkResult<UserInfoDto> = onIo {
        return@onIo getNetworkResult {
            httpClient.get("/user").body<UserInfoDto>()
        }
    }

    suspend fun createUser(requestDto: UserInfoCreationRequestDto): NetworkResult<UserInfoDto> = onIo {
        return@onIo getNetworkResult {
            httpClient.post("/user") {
                setBody(requestDto)
            }.body<UserInfoDto>()
        }
    }
}