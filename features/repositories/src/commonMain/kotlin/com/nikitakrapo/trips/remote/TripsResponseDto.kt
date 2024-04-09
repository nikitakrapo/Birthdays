package com.nikitakrapo.trips.remote

import com.nikitakrapo.trips.dto.TripDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TripsResponseDto(
    @SerialName("trips") val trips: List<TripDto>,
)
