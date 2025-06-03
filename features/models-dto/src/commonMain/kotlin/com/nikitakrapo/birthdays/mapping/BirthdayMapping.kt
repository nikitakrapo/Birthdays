package com.nikitakrapo.birthdays.mapping

import com.nikitakrapo.birthdays.dto.BirthdayDto
import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.datetime.LocalDate

fun BirthdayDto.toBirthday(): Birthday? {
    return Birthday(
        id = id ?: return null,
        title = displayName ?: return null,
        date = birthdayDate?.let { LocalDate.parse(it, LocalDate.Formats.ISO) } ?: return null,
        imageUrl = null,
    )
}
