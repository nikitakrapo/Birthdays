package com.nikitakrapo.trips

import com.nikitakrapo.trips.remote.TripsApi
import org.koin.dsl.module

val repositoriesModule = module {
    factory<TripsApi> { TripsApi(get()) }
}