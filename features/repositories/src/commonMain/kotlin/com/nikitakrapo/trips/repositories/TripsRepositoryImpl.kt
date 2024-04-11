package com.nikitakrapo.trips.repositories

import com.nikitakrapo.trips.di.Di
import com.nikitakrapo.trips.dto.TripDto
import com.nikitakrapo.trips.mapping.toTrip
import com.nikitakrapo.trips.model.Trip
import com.nikitakrapo.trips.model.TripCreate
import com.nikitakrapo.trips.network.result.toResult
import com.nikitakrapo.trips.remote.TripCreateDto
import com.nikitakrapo.trips.remote.TripPostRequestDto
import com.nikitakrapo.trips.remote.TripsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TripsRepositoryImpl : TripsRepository {

    private val api by Di.inject<TripsApi>()

    override fun getTripsFlow(): Flow<List<Trip>> {
        return flowOf()
    }

    override suspend fun fetchTrips(): Result<List<Trip>> {
        return api.getUserTrips()
            .toResult()
            .map { it.trips.map(TripDto::toTrip) }
    }

    override suspend fun addTrip(trip: TripCreate): Result<Unit> {
        val request = TripPostRequestDto(
            trip = TripCreateDto(title = trip.title)
        )
        return api.addUserTrip(request).toResult()
    }
}