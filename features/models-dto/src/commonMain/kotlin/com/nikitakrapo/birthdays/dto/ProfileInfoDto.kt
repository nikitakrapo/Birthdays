package com.nikitakrapo.birthdays.dto

import kotlinx.serialization.SerialName

class ProfileInfoDto(
    @SerialName("username") val username: String?,
    @SerialName("birthday") val birthday: String?,
)
