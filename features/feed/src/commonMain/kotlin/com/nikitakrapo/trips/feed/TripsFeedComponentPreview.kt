package com.nikitakrapo.trips.feed

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object TripsFeedComponentPreview : TripsFeedComponent {

    override val state: StateFlow<TripsFeedScreenState> = MutableStateFlow(
        TripsFeedScreenState.Loading
    )

    override fun onAddTripClicked() {}
}