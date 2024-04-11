package com.nikitakrapo.trips.feed

import androidx.compose.runtime.Stable
import com.nikitakrapo.trips.feed.item.TripItemComponent
import kotlinx.coroutines.flow.StateFlow

@Stable
interface TripsFeedComponent {

    val state: StateFlow<TripsFeedScreenState>

    fun onAddTripClicked()

    fun createTripItemComponent(index: Int): TripItemComponent
}