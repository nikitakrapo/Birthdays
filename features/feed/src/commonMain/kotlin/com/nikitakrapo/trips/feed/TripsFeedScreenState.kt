package com.nikitakrapo.trips.feed

import com.nikitakrapo.trips.model.Trip

sealed interface TripsFeedScreenState {

    data object Loading : TripsFeedScreenState

    data class Loaded(
        val trips: List<Trip>,
    ) : TripsFeedScreenState
}