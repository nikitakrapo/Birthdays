package com.nikitakrapo.birthdays

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.char

data class AddBirthdayState(
    val nameText: String,
    val date: LocalDate?,
    val isAddActive: Boolean,
    val yearRange: IntRange,
    val lastSelectableDatetimeMs: Long,
    val error: String?,
) {

    val dateText = date?.format(LocalDate.Format {
        dayOfMonth()
        char('-')
        monthNumber()
        char('-')
        year()
    })
}
