package com.nikitakrapo.birthdays.remote

import com.nikitakrapo.birthdays.network.result.NetworkResult
import com.nikitakrapo.birthdays.network.result.TripsBackendResponse
import com.nikitakrapo.birthdays.network.result.wrapWithNetworkResult
import com.nikitakrapo.birthdays.utils.coroutines.onIo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal class TripsApi(
    private val httpClient: HttpClient,
) {

    suspend fun getUserTrips(): NetworkResult<TripsResponseDto> = onIo {
        wrapWithNetworkResult {
            httpClient.get("trips")
                .body<TripsBackendResponse<TripsResponseDto>>()
        }
    }

    suspend fun addUserTrip(request: TripPostRequestDto): NetworkResult<Unit> = onIo {
        wrapWithNetworkResult {
            httpClient.post("trips") {
                setBody(request)
            }.body<TripsBackendResponse<Unit>>()
        }
    }
}