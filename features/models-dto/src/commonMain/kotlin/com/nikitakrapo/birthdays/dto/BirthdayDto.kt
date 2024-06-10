package com.nikitakrapo.birthdays.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BirthdayDto(
    @SerialName("id") val id: String?,
    @SerialName("title") val title: String?,
    @SerialName("date") val date: String?,
    @SerialName("imageUrl") val imageUrl: String?,
)