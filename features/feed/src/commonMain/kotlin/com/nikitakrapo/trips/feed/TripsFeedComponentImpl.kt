package com.nikitakrapo.trips.feed

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.trips.di.Di
import com.nikitakrapo.trips.feed.item.TripItemComponent
import com.nikitakrapo.trips.feed.item.TripItemComponentImpl
import com.nikitakrapo.trips.model.TripCreate
import com.nikitakrapo.trips.repositories.TripsRepository
import com.nikitakrapo.trips.utils.coroutines.collectIn
import com.nikitakrapo.trips.utils.decompose.coroutineScope
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

    override fun createTripItemComponent(index: Int): TripItemComponent {
        val trip = (state.value as TripsFeedScreenState.Loaded).trips[index]
        return TripItemComponentImpl(
            trip = trip,
            onTripClick = {}
        )
    }
}