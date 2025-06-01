package com.nikitakrapo.birthdays.remote.data

import kotlinx.serialization.SerialName

class AddBirthdayDto(
    @SerialName("date") val date: String,
    @SerialName("name") val name: String,
)
