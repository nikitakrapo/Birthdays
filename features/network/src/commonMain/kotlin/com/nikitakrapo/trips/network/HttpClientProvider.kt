package com.nikitakrapo.trips.network

import com.nikitakrapo.trips.di.Di
import io.github.aakira.napier.Napier
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientProvider {

    private val tokenProvider by Di.inject<AuthorizationTokenProvider>()

    fun httpClient() = httpClient {
        install(DefaultRequest) {
            url(NetworkConfig.baseUrl)
        }
        install(ContentNegotiation) {
            json(json = Di.get<Json>())
        }
        install(Logging) {
            level = NetworkConfig.logLevel
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.d(message)
                }
            }
        }
    }.apply {
        plugin(HttpSend).intercept { request ->
            tokenProvider.getToken()?.let { token ->
                request.header(HttpHeaders.Authorization, "Bearer $token")
            }
            execute(request)
        }
    }
}