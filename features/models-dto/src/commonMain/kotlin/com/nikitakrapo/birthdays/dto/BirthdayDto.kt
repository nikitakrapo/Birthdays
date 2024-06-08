package com.nikitakrapo.birthdays.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BirthdayDto(
    @SerialName("date") val date: String?,
)