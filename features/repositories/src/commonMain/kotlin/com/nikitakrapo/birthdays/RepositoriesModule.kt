package com.nikitakrapo.birthdays

import com.nikitakrapo.birthdays.remote.TripsApi
import com.nikitakrapo.birthdays.repositories.profile.FakeProfileRepository
import com.nikitakrapo.birthdays.repositories.profile.ProfileRepository
import com.nikitakrapo.birthdays.repositories.trips.FakeTripsRepository
import com.nikitakrapo.birthdays.repositories.trips.TripsRepository
import org.koin.dsl.module

val repositoriesModule = module {
    factory<TripsApi> { TripsApi(get()) }
    single<ProfileRepository> { FakeProfileRepository() }
    factory<TripsRepository> { FakeTripsRepository() }
}