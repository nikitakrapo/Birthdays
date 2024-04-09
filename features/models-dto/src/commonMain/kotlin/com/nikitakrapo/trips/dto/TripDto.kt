package com.nikitakrapo.trips.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TripDto(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
)
