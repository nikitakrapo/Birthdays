package com.nikitakrapo.birthdays.repositories

import com.nikitakrapo.birthdays.di.Di
import com.nikitakrapo.birthdays.dto.TripDto
import com.nikitakrapo.birthdays.mapping.toTrip
import com.nikitakrapo.birthdays.model.Trip
import com.nikitakrapo.birthdays.model.TripCreate
import com.nikitakrapo.birthdays.network.result.toResult
import com.nikitakrapo.birthdays.remote.TripCreateDto
import com.nikitakrapo.birthdays.remote.TripPostRequestDto
import com.nikitakrapo.birthdays.remote.TripsApi
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