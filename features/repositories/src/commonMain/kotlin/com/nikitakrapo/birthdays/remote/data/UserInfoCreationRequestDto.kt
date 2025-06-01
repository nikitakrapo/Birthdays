package com.nikitakrapo.birthdays.remote.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoCreationRequestDto(
    @SerialName("displayName") val displayName: String,
    @SerialName("birthdayDate") val birthdayDate: String,
)
