package com.nikitakrapo.trips.mapping

import com.nikitakrapo.trips.dto.TripDto
import com.nikitakrapo.trips.model.Trip
import kotlinx.datetime.Instant

fun TripDto.toTrip() = Trip(
    id = id,
    title = title,
    startDatetime = Instant.parse(startDatetime),
    endDatetime = Instant.parse(endDatetime),
)
