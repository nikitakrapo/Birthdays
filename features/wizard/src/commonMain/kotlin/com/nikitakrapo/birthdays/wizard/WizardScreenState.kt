package com.nikitakrapo.birthdays.wizard

import kotlinx.datetime.LocalDateTime

data class WizardScreenState(
    val selectedDate: LocalDateTime,
    val selectableDateTimes: Pair<LocalDateTime, LocalDateTime>,
) {
    val yearRange = selectableDateTimes.first.year..selectableDateTimes.second.year
}
