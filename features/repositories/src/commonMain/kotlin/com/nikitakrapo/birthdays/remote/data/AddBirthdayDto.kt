package com.nikitakrapo.birthdays.remote.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AddBirthdayDto(
    @SerialName("displayName") val displayName: String,
    @SerialName("birthdayDate") val displayDate: String,
)
