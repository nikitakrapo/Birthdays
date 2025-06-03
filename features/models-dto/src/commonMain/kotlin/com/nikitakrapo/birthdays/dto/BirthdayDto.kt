package com.nikitakrapo.birthdays.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BirthdayDto(
    @SerialName("id") val id: String?,
    @SerialName("displayName") val displayName: String?,
    @SerialName("birthdayDate") val birthdayDate: String?,
)