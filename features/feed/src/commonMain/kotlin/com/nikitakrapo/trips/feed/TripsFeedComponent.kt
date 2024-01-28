package com.nikitakrapo.trips.feed

import kotlinx.coroutines.flow.StateFlow

interface TripsFeedComponent {

    val state: StateFlow<TripsFeedScreenState>

    fun onAddTripClicked()
}