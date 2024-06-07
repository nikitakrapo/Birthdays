package com.nikitakrapo.birthdays.feed

import com.nikitakrapo.birthdays.model.Trip

sealed interface TripsFeedScreenState {

    data object Loading : TripsFeedScreenState

    data class Loaded(
        val trips: List<Trip>,
    ) : TripsFeedScreenState
}