package com.nikitakrapo.birthdays.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(Darwin) {
        config()
        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
    }
}
