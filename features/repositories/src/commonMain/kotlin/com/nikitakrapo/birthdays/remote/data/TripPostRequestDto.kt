package com.nikitakrapo.birthdays.remote.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TripPostRequestDto(
    @SerialName("trip") val trip: TripCreateDto,
)

@Serializable
internal data class TripCreateDto(
    @SerialName("title") val title: String,
)
