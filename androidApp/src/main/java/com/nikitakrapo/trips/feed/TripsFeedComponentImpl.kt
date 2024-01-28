package com.nikitakrapo.trips.feed

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TripsFeedComponentImpl(
    componentContext: ComponentContext,
) : TripsFeedComponent, ComponentContext by componentContext {

    override val state: StateFlow<TripsFeedScreenState> = MutableStateFlow(
        TripsFeedScreenState.Loading
    )

    override fun onAddTripClicked() {}
}