package com.nikitakrapo.birthdays.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
class ProfileInfo(
    val username: String,
    val birthday: LocalDate,
)
