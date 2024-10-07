package com.nikitakrapo.birthdays.di

import com.nikitakrapo.birthdays.cms.pushModule
import com.nikitakrapo.birthdays.feed.birthdaysFeedModule
import com.nikitakrapo.birthdays.network.AuthorizationTokenProvider
import com.nikitakrapo.birthdays.network.AuthorizationTokenProviderImpl
import com.nikitakrapo.birthdays.network.HttpClientProvider
import com.nikitakrapo.birthdays.network.networkModule
import com.nikitakrapo.birthdays.platform.PlatformContext
import com.nikitakrapo.birthdays.repositoriesModule
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.dsl.module

object AppDi {

    fun start(platformContext: PlatformContext) {
        startKoin {
            modules(
                appModule(platformContext),
                pushModule,
                authorizationModule,
                birthdaysFeedModule,
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