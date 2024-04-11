package com.nikitakrapo.trips.feed

import com.nikitakrapo.trips.feed.item.TripItemComponentPreview
import com.nikitakrapo.trips.model.Trip
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TripsFeedComponentPreview(
    state: TripsFeedScreenState = TripsFeedScreenState.Loaded(
        List(10) {
            Trip(it.toString(), "Trip $it")
        }
    ),
) : TripsFeedComponent {

    override val state: StateFlow<TripsFeedScreenState> = MutableStateFlow(state)

    override fun onAddTripClicked() {}

    override fun createTripItemComponent(index: Int) = TripItemComponentPreview
}