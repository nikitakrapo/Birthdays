package com.nikitakrapo.trips.repositories

import com.nikitakrapo.trips.model.Trip
import com.nikitakrapo.trips.model.TripCreate
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import java.util.UUID

class FakeTripsRepository : TripsRepository {

    private var prevTrips: List<Trip> = listOf(
        Trip("1", "Title 1")
    )
    private val tripsFlow = MutableSharedFlow<List<Trip>>()

    override fun getTripsFlow(): Flow<List<Trip>> {
        return tripsFlow
            .onStart { emit(prevTrips) }
            .onEach { Napier.d("getTripsFlow: new trip") }
    }

    override suspend fun fetchTrips(): Result<List<Trip>> {
        return Result.success(prevTrips)
    }

    override suspend fun addTrip(trip: TripCreate): Result<Unit> {
        val newTrips = prevTrips.toMutableList().apply {
            add(Trip(id = UUID.randomUUID().toString(), title = trip.title))
        }
        prevTrips = newTrips
        tripsFlow.emit(newTrips)
        return Result.success(Unit)
    }
}