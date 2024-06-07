package com.nikitakrapo.birthdays.feed

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.birthdays.di.Di
import com.nikitakrapo.birthdays.feed.item.TripItemComponent
import com.nikitakrapo.birthdays.feed.item.TripItemComponentImpl
import com.nikitakrapo.birthdays.model.Trip
import com.nikitakrapo.birthdays.model.TripCreate
import com.nikitakrapo.birthdays.repositories.trips.TripsRepository
import com.nikitakrapo.birthdays.utils.coroutines.collectIn
import com.nikitakrapo.birthdays.utils.decompose.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class TripsFeedComponentImpl(
    componentContext: ComponentContext,
) : TripsFeedComponent, ComponentContext by componentContext {

    private val repository by Di.inject<TripsRepository>()

    private val scope = coroutineScope()

    private val stateFlow = MutableStateFlow<TripsFeedScreenState>(TripsFeedScreenState.Loading)
    override val state: StateFlow<TripsFeedScreenState> = stateFlow.asStateFlow()

    init {
        repository.getTripsFlow().collectIn(scope) {
            stateFlow.value = TripsFeedScreenState.Loaded(it)
        }
    }

    override fun onAddTripClicked() {
        val newTrip = TripCreate(title = "SAMPLE ${Random.nextInt()}")
        scope.launch(Dispatchers.IO) { repository.addTrip(newTrip) }
    }

    override fun createTripItemComponent(trip: Trip): TripItemComponent {
        return TripItemComponentImpl(
            trip = trip,
            onTripClick = { TODO() }
        )
    }
}