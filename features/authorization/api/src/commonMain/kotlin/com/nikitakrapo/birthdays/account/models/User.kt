package com.nikitakrapo.birthdays.account.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("uid") val uid: String,
    @SerialName("displayName") val displayName: String,
    @SerialName("birthdayDate") val birthdayDate: LocalDate,
)
