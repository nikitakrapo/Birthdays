package com.nikitakrapo.birthdays.mapping

import com.nikitakrapo.birthdays.dto.ProfileInfoDto
import com.nikitakrapo.birthdays.model.ProfileInfo
import kotlinx.datetime.LocalDate

fun ProfileInfoDto.toProfileInfo(): ProfileInfo? {
    return ProfileInfo(
        username = username ?: return null,
        birthday = birthday?.let { LocalDate.parse(it, LocalDate.Formats.ISO) } ?: return null,
    )
}
