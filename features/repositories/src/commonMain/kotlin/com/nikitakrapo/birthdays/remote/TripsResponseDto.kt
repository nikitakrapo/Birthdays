package com.nikitakrapo.birthdays.remote

import com.nikitakrapo.birthdays.dto.TripDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TripsResponseDto(
    @SerialName("trips") val trips: List<TripDto>,
)
