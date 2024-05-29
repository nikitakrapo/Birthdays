package com.nikitakrapo.birthdays.cms

interface PushTokenInteractor {

    suspend fun sendTokenToServer()

    suspend fun onTokenChanged(token: String)
}