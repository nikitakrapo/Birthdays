package com.nikitakrapo.birthdays.cms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PushTokenUpdateRequest(
    @SerialName("deviceId") val deviceId: String,
    @SerialName("token") val token: String,
)
