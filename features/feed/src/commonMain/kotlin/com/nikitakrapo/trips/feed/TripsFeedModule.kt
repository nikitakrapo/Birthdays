package com.nikitakrapo.trips.feed

import com.nikitakrapo.trips.feed.data.TripsFeedDataSource
import org.koin.dsl.module

val tripsFeedModule = module {
    single<TripsFeedDataSource> { TripsFeedDataSource() }
}