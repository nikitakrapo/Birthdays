package com.nikitakrapo.birthdays.remote

import com.nikitakrapo.birthdays.mapping.toBirthday
import com.nikitakrapo.birthdays.model.Birthday
import com.nikitakrapo.birthdays.network.result.NetworkResult
import com.nikitakrapo.birthdays.network.result.getNetworkResult
import com.nikitakrapo.birthdays.remote.data.BirthdaysResponseDto
import com.nikitakrapo.birthdays.utils.coroutines.onIo
import com.nikitakrapo.network.utils.paging.PagingQueryParameters
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.SerializationException

internal class BirthdaysApi(
    private val httpClient: HttpClient,
) {

    suspend fun getBirthdays(
        uid: String,
        page: Int,
        pageSize: Int,
    ): NetworkResult<List<Birthday>> = onIo {
        return@onIo getNetworkResult {
            val response = httpClient.get("users/$uid/birthdays") {
                parameter(PagingQueryParameters.PAGE, page)
                parameter(PagingQueryParameters.PAGE_SIZE, pageSize)
            }
                .body<BirthdaysResponseDto>()
            response.birthdays?.mapNotNull { it.toBirthday() }
                ?: throw SerializationException()
        }
    }
}