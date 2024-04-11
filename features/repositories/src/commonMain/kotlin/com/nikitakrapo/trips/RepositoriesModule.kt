package com.nikitakrapo.trips

import com.nikitakrapo.trips.remote.TripsApi
import com.nikitakrapo.trips.repositories.FakeTripsRepository
import com.nikitakrapo.trips.repositories.TripsRepository
import org.koin.dsl.module

val repositoriesModule = module {
    factory<TripsApi> { TripsApi(get()) }
//    factory<TripsRepository> { TripsRepositoryImpl() }
    factory<TripsRepository> { FakeTripsRepository() }
}