package com.nikitakrapo.birthdays

import com.nikitakrapo.birthdays.remote.TripsApi
import com.nikitakrapo.birthdays.repositories.FakeTripsRepository
import com.nikitakrapo.birthdays.repositories.TripsRepository
import org.koin.dsl.module

val repositoriesModule = module {
    factory<TripsApi> { TripsApi(get()) }
//    factory<TripsRepository> { TripsRepositoryImpl() }
    factory<TripsRepository> { FakeTripsRepository() }
}