package com.nikitakrapo.trips.android

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.nikitakrapo.birthdays.cms.PushTokenInteractor
import com.nikitakrapo.trips.di.Di
import io.github.aakira.napier.Napier
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BirthdaysFirebaseMessagingService : FirebaseMessagingService() {

    private val pushTokenInteractor by Di.inject<PushTokenInteractor>()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Napier.d { "New FCM token: $token" }
        GlobalScope.launch {
            pushTokenInteractor.onTokenChanged(token)
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Napier.d { "New FCM remote message: ${remoteMessage.data}" }
    }
}