package com.nikitakrapo.birthdays.network

import io.ktor.client.plugins.logging.LogLevel

object NetworkConfig {
    val baseUrl = "http://api.nikitakrapo.com"
    val logLevel = LogLevel.INFO
}