package com.nikitakrapo.trips.repositories

import com.nikitakrapo.trips.model.Trip
import com.nikitakrapo.trips.model.TripCreate
import kotlinx.coroutines.flow.Flow

interface TripsRepository {

    fun getTripsFlow(): Flow<List<Trip>>

    suspend fun fetchTrips(): Result<List<Trip>>

    suspend fun addTrip(trip: TripCreate): Result<Unit>
}