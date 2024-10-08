package com.nikitakrapo.birthdays.profile

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char

internal fun LocalDate.getProfileBirthdayText(): String {
    return format(LocalDate.Format {
        dayOfMonth()
        char(' ')
        // TODO TRIPSMOBILE-12: add localization
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        char(' ')
        year()
    })
}