package com.nikitakrapo.birthdays.components.calendar.data

import kotlinx.datetime.LocalDate

class DayState(
    val day: LocalDate,
    val isChosen: Boolean,
    val isActive: Boolean,
)
