package com.nikitakrapo.trips.network

import io.github.aakira.napier.Napier
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

object NetworkClientProvider {
    fun httpClient() = httpClient {
        install(Logging) {
            level = LogLevel.INFO
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.d(message)
                }
            }
        }
    }
}