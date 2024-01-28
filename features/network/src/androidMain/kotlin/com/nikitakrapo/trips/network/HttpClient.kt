package com.nikitakrapo.trips.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(OkHttp) {
        config()
        engine {
            config {
                retryOnConnectionFailure(true)
                connectTimeout(5.seconds.toJavaDuration())
            }
        }
    }
}
