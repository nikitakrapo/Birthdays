package com.nikitakrapo.birthdays.account.cache

import com.nikitakrapo.birthdays.account.models.User
import com.nikitakrapo.birthdays.di.Di
import com.nikitakrapo.birthdays.platform.createSettings
import com.russhwolf.settings.set
import io.github.aakira.napier.Napier
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class UserCache {

    private val settings by lazy { createSettings("user_cache", Di.get()) }
    private val json: Json by Di.inject()

    var user: User?
        get() {
            val userJson = settings.getStringOrNull(USER_KEY) ?: return null
            return try {
                json.decodeFromString(userJson)
            } catch (e: SerializationException) {
                Napier.e { "Failed to decode user from cache: $e" }
                null
            } catch (e: IllegalArgumentException) {
                Napier.e { "Failed to decode user from cache: $e" }
                null
            }
        }
        set(value) {
            val userJson = json.encodeToString(value)
            settings.set(USER_KEY, userJson)
        }

    companion object {

        private const val USER_KEY = "user"
    }
}