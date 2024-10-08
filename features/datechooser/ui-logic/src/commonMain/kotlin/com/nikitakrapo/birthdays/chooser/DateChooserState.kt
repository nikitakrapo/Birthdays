package com.nikitakrapo.birthdays.chooser

import kotlinx.datetime.LocalDate

data class DateChooserState(
    val initialDate: LocalDate,
    val initialSelectedDate: LocalDate?,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val title: String,
    val mode: ChooserMode,
) {
    enum class ChooserMode {
        Calendar,
        Text,
        ;
    }
}
