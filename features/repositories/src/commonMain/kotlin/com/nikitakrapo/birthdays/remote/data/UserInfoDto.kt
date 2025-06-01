package com.nikitakrapo.birthdays.remote.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserInfoDto(
    @SerialName("uid") val uid: String,
    @SerialName("displayName") val displayName: String,
    @SerialName("birthdayDate") val birthdayDate: String,
)
