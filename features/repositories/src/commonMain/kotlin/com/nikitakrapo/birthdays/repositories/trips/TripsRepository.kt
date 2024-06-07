package com.nikitakrapo.birthdays.repositories.trips

import com.nikitakrapo.birthdays.model.Trip
import com.nikitakrapo.birthdays.model.TripCreate
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.toInstant
import java.util.UUID

interface TripsRepository {

    fun getTripsFlow(): Flow<List<Trip>>

    suspend fun fetchTrips(): Result<List<Trip>>

    suspend fun addTrip(trip: TripCreate): Result<Unit>
}

class FakeTripsRepository : TripsRepository {

    private var prevTrips: List<Trip> = listOf(
        Trip(
            id = "1",
            title = "Title 1",
            startDatetime = Instant.fromEpochMilliseconds(1713096068L),
            endDatetime = Instant.fromEpochSeconds(1712829639L)
        )
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
        val time = LocalTime(0, 0, 0, 0)
        val startDatetime = LocalDateTime(LocalDate(2024, 4, 11), time)
        val endDatetime = LocalDateTime(LocalDate(2024, 4, 15), time)
        val newTrips = prevTrips.toMutableList().apply {
            add(Trip(
                id = UUID.randomUUID().toString(),
                title = trip.title,
                startDatetime = startDatetime.toInstant(UtcOffset.ZERO),
                endDatetime = endDatetime.toInstant(UtcOffset.ZERO),
            ))
        }
        prevTrips = newTrips
        tripsFlow.emit(newTrips)
        return Result.success(Unit)
    }
}