package com.nikitakrapo.birthdays.network

import io.ktor.client.plugins.logging.LogLevel

object NetworkConfig {
    val baseUrl = "http://45.12.5.87:80"
    val logLevel = LogLevel.INFO
}