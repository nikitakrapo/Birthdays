package com.nikitakrapo.birthdays.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

internal expect fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient
