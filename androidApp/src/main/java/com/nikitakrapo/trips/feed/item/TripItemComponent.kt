package com.nikitakrapo.trips.feed.item

import kotlinx.coroutines.flow.StateFlow

interface TripItemComponent {
    val state: StateFlow<TripItemState>

    fun onTripClick()
    fun onOverflowClick()
}