package com.nikitakrapo.birthdays.network

import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<Json> {
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }
    }
}