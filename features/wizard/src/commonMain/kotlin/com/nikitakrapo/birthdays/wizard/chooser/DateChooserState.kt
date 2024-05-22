package com.nikitakrapo.birthdays.wizard.chooser

import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDate

@Stable
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
