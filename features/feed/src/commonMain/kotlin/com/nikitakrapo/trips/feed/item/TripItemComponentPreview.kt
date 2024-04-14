package com.nikitakrapo.trips.feed.item

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object TripItemComponentPreview : TripItemComponent {
    override val state: StateFlow<TripItemState> = MutableStateFlow(
        TripItemState("Trip to Barcelona", "3 Dec - 9 Dec")
    )

    override fun onTripClick() {}
}