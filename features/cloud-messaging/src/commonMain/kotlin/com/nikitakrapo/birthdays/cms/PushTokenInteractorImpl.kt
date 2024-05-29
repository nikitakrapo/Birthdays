package com.nikitakrapo.birthdays.cms

import com.nikitakrapo.birthdays.platform.getDeviceId
import com.nikitakrapo.trips.di.Di
import com.nikitakrapo.trips.utils.coroutines.onIo
import com.russhwolf.settings.set
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class PushTokenInteractorImpl(
    private val httpClient: HttpClient,
) : PushTokenInteractor {

    private val pushTokenSettings = pushTokenSettings(Di.get())

    override suspend fun sendTokenToServer(): Unit = onIo {
        val request = PushTokenUpdateRequest(
            deviceId = getDeviceId(Di.get()).orEmpty(),
            token = pushTokenSettings.getString(PUSH_TOKEN, ""),
        )

        try {
            httpClient.post("/push/update-token") {
                setBody<PushTokenUpdateRequest>(request)
            }
        } catch (e: Exception) {
            Napier.e(e) { "Error while updating push token" }
        }
    }

    override suspend fun onTokenChanged(token: String) {
        pushTokenSettings[PUSH_TOKEN] = token
    }

    companion object {
        private const val PUSH_TOKEN = "push_token"
    }
}