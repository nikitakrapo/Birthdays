package com.nikitakrapo.birthdays.remote.data

import com.nikitakrapo.birthdays.dto.BirthdayDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BirthdaysResponseDto(
    @SerialName("birthdays") val birthdays: List<BirthdayDto>?,
)
