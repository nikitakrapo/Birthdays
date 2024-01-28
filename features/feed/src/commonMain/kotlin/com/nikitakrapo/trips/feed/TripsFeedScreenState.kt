package com.nikitakrapo.trips.feed

sealed interface TripsFeedScreenState {

    data object Loading : TripsFeedScreenState
}