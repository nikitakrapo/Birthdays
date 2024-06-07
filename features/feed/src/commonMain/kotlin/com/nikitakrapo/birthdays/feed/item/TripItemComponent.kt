package com.nikitakrapo.birthdays.feed.item

import kotlinx.coroutines.flow.StateFlow

interface TripItemComponent {
    val state: StateFlow<TripItemState>

    fun onTripClick()
}