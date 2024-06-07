package com.nikitakrapo.birthdays.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TripPostRequestDto(
    @SerialName("trip") val trip: TripCreateDto,
)

@Serializable
data class TripCreateDto(
    @SerialName("title") val title: String,
)
