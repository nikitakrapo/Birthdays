package com.nikitakrapo.birthdays.feed

import com.nikitakrapo.birthdays.feed.item.TripItemComponentPreview
import com.nikitakrapo.birthdays.model.Trip
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Instant

class TripsFeedComponentPreview(
    state: TripsFeedScreenState = TripsFeedScreenState.Loaded(
        List(10) {
            Trip(
                id = it.toString(),
                title = "Trip $it",
                startDatetime = Instant.fromEpochMilliseconds(1713096068L),
                endDatetime = Instant.fromEpochSeconds(1712829639L),
            )
        }
    ),
) : TripsFeedComponent {

    override val state: StateFlow<TripsFeedScreenState> = MutableStateFlow(state)

    override fun onAddTripClicked() {}

    override fun createTripItemComponent(trip: Trip) = TripItemComponentPreview
}