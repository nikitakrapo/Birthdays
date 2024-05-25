package com.nikitakrapo.trips.di

import com.nikitakrapo.birthdays.account.accountModule
import com.nikitakrapo.birthdays.account.AccountManager
import com.nikitakrapo.trips.feed.tripsFeedModule
import com.nikitakrapo.trips.network.AuthorizationTokenProvider
import com.nikitakrapo.trips.network.AuthorizationTokenProviderImpl
import com.nikitakrapo.trips.network.NetworkClientProvider
import com.nikitakrapo.trips.network.networkModule
import com.nikitakrapo.trips.repositoriesModule
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.dsl.module

object AppDi {
    fun start() {
        startKoin {
            modules(
                appModule,
                accountModule,
                tripsFeedModule,
                networkModule,
                repositoriesModule,
            )
        }
    }
}

private val appModule = module {
    single<HttpClient> { NetworkClientProvider.httpClient() }
    single<AuthorizationTokenProvider> { AuthorizationTokenProviderImpl(get()) }
}