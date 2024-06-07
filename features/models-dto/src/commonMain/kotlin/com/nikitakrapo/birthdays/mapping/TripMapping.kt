package com.nikitakrapo.birthdays.mapping

import com.nikitakrapo.birthdays.dto.TripDto
import com.nikitakrapo.birthdays.model.Trip
import kotlinx.datetime.Instant

fun TripDto.toTrip() = Trip(
    id = id,
    title = title,
    startDatetime = Instant.parse(startDatetime),
    endDatetime = Instant.parse(endDatetime),
)
