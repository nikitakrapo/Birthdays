package com.nikitakrapo.birthdays.repositories

import com.nikitakrapo.birthdays.model.Trip
import com.nikitakrapo.birthdays.model.TripCreate
import kotlinx.coroutines.flow.Flow

interface TripsRepository {

    fun getTripsFlow(): Flow<List<Trip>>

    suspend fun fetchTrips(): Result<List<Trip>>

    suspend fun addTrip(trip: TripCreate): Result<Unit>
}