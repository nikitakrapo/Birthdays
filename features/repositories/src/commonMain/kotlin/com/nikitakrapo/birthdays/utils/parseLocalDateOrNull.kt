package com.nikitakrapo.birthdays.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format

internal fun String.parseLocalDateOrNull(): LocalDate? {
    return try {
        LocalDate.parse(this)
    } catch (e: IllegalArgumentException) {
        null
    }
}

internal fun LocalDate.encodeAsISO(): String? {
    return try {
        format(LocalDate.Formats.ISO)
    } catch (e: IllegalArgumentException) {
        null
    }
}