package com.nikitakrapo.trips.feed.item

import com.nikitakrapo.trips.model.Trip
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TripItemComponentImpl(
    private val trip: Trip,
    private val onTripClick: (Trip) -> Unit,
) : TripItemComponent {

    private val stateFlow = MutableStateFlow(TripItemState.fromTrip(trip))
    override val state: StateFlow<TripItemState> = stateFlow.asStateFlow()

    override fun onTripClick() {
        onTripClick(trip)
    }
}