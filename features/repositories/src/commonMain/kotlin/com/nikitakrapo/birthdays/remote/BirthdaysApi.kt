package com.nikitakrapo.birthdays.remote

import com.nikitakrapo.birthdays.mapping.toBirthday
import com.nikitakrapo.birthdays.model.Birthday
import com.nikitakrapo.birthdays.network.result.NetworkResult
import com.nikitakrapo.birthdays.network.result.getNetworkResult
import com.nikitakrapo.birthdays.remote.data.AddBirthdayDto
import com.nikitakrapo.birthdays.remote.data.BirthdaysResponseDto
import com.nikitakrapo.birthdays.utils.coroutines.onIo
import com.nikitakrapo.network.utils.paging.PagingQueryParameters
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.SerializationException

internal class BirthdaysApi(
    private val httpClient: HttpClient,
) {

    suspend fun addLocalBirthday(
        request: AddBirthdayDto,
    ): NetworkResult<Unit> = onIo {
        return@onIo getNetworkResult {
            httpClient.post("birthdays") {
                setBody(request)
            }
            Unit
        }
    }

    suspend fun getBirthdays(
        offset: Int,
        count: Int,
    ): NetworkResult<List<Birthday>> = onIo {
        return@onIo getNetworkResult {
            val response = httpClient.get("birthdays") {
                parameter(PagingQueryParameters.OFFSET, offset)
                parameter(PagingQueryParameters.COUNT, count)
            }.body<BirthdaysResponseDto>()
            response.birthdays?.mapNotNull { it.toBirthday() }
                ?: throw SerializationException()
        }
    }
}