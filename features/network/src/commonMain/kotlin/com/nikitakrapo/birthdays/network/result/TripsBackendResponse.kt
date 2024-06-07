package com.nikitakrapo.birthdays.network.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TripsBackendResponse<T>(
    @SerialName("result") val result: T?,
)