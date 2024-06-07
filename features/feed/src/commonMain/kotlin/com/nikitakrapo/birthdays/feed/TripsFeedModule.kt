package com.nikitakrapo.birthdays.feed

import com.nikitakrapo.birthdays.feed.data.TripsFeedDataSource
import org.koin.dsl.module

val tripsFeedModule = module {
    single<TripsFeedDataSource> { TripsFeedDataSource() }
}