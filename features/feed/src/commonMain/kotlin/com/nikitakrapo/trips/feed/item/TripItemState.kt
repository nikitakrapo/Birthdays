package com.nikitakrapo.trips.feed.item

import com.nikitakrapo.trips.model.Trip

data class TripItemState(
    val title: String,
) {
    companion object {
        fun fromTrip(trip: Trip) = TripItemState(
            title = trip.title,
        )
    }
}
