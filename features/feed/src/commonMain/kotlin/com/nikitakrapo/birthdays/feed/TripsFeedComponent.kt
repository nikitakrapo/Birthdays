package com.nikitakrapo.birthdays.feed

import androidx.compose.runtime.Stable
import com.nikitakrapo.birthdays.feed.item.TripItemComponent
import com.nikitakrapo.birthdays.model.Trip
import kotlinx.coroutines.flow.StateFlow

@Stable
interface TripsFeedComponent {

    val state: StateFlow<TripsFeedScreenState>

    fun onAddTripClicked()

    fun createTripItemComponent(trip: Trip): TripItemComponent
}