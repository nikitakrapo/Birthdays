package com.nikitakrapo.birthdays.remote.data

import com.nikitakrapo.birthdays.dto.TripDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TripsResponseDto(
    @SerialName("trips") val trips: List<TripDto>,
)
