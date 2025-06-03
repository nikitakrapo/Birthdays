package com.nikitakrapo.birthdays.remote.data

import com.nikitakrapo.birthdays.dto.BirthdayDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AddBirthdayDto(
    @SerialName("displayName") val displayName: String,
    @SerialName("birthdayDate") val displayDate: String,
)

@Serializable
class AddBirthdayFullBirthdayDto(
    @SerialName("birthday") val birthday: BirthdayDto,
)
