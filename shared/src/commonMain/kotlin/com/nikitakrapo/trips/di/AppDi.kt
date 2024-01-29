package com.nikitakrapo.trips.di

import com.nikitakrapo.trips.account.AccountManager
import com.nikitakrapo.trips.network.NetworkClientProvider
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.mp.KoinPlatformTools

val Di = KoinPlatformTools.defaultContext().get()

object AppDi {
    fun start() {
        startKoin {
            modules(appModule)
        }
    }
}

private val appModule = module {
    single<HttpClient> { NetworkClientProvider.httpClient() }
    single<AccountManager> { AccountManager() }
}