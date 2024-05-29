package com.nikitakrapo.trips.di

import com.nikitakrapo.birthdays.account.accountModule
import com.nikitakrapo.birthdays.cms.pushModule
import com.nikitakrapo.birthdays.platform.PlatformContext
import com.nikitakrapo.trips.feed.tripsFeedModule
import com.nikitakrapo.trips.network.AuthorizationTokenProvider
import com.nikitakrapo.trips.network.AuthorizationTokenProviderImpl
import com.nikitakrapo.trips.network.HttpClientProvider
import com.nikitakrapo.trips.network.networkModule
import com.nikitakrapo.trips.repositoriesModule
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.dsl.module

object AppDi {

    fun start(platformContext: PlatformContext) {
        startKoin {
            modules(
                appModule(platformContext),
                pushModule,
                accountModule,
                tripsFeedModule,
                networkModule,
                repositoriesModule,
            )
        }
    }
}

private fun appModule(platformContext: PlatformContext) = module {
    single<HttpClient> { HttpClientProvider.httpClient() }
    single<AuthorizationTokenProvider> { AuthorizationTokenProviderImpl(get()) }
    single<PlatformContext> { platformContext }
}