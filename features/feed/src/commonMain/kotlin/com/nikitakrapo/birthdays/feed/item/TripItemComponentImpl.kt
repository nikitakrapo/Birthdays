package com.nikitakrapo.birthdays.feed.item

import com.nikitakrapo.birthdays.model.Trip
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TripItemComponentImpl(
    private val trip: Trip,
    private val onTripClick: (Trip) -> Unit,
) : TripItemComponent {

    private val stateFlow = MutableStateFlow(trip.toItemState())
    override val state: StateFlow<TripItemState> = stateFlow.asStateFlow()

    override fun onTripClick() {
        onTripClick(trip)
    }
}