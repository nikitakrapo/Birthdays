package com.nikitakrapo.trips.mapping

import com.nikitakrapo.trips.dto.TripDto
import com.nikitakrapo.trips.model.Trip

fun TripDto.toTrip() = Trip(
    id = id,
    title = title,
)
