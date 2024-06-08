package com.nikitakrapo.birthdays.mapping

import com.nikitakrapo.birthdays.dto.BirthdayDto
import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.datetime.LocalDate

fun BirthdayDto.toBirthday(): Birthday? {
    return Birthday(
        date = date?.let { LocalDate.parse(it, LocalDate.Formats.ISO) } ?: return null,
    )
}
