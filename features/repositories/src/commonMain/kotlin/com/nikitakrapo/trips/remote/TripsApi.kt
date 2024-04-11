package com.nikitakrapo.trips.remote

import com.nikitakrapo.trips.network.result.NetworkResult
import com.nikitakrapo.trips.network.result.TripsBackendResponse
import com.nikitakrapo.trips.network.result.wrapWithNetworkResult
import com.nikitakrapo.trips.utils.coroutines.onIo
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