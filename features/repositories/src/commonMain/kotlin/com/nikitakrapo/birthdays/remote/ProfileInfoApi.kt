package com.nikitakrapo.birthdays.remote

import com.nikitakrapo.birthdays.dto.ProfileInfoDto
import com.nikitakrapo.birthdays.network.result.NetworkResult
import com.nikitakrapo.birthdays.network.result.getNetworkResult
import com.nikitakrapo.birthdays.utils.coroutines.onIo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class ProfileInfoApi(
    private val httpClient: HttpClient,
) {

    suspend fun getProfileInfo(uid: String): NetworkResult<ProfileInfoDto> = onIo {
        getNetworkResult {
            httpClient.get("users/$uid/profile")
                .body<ProfileInfoDto>()
        }
    }
}