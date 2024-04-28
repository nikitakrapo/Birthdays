package com.nikitakrapo.birthdays.wizard.chooser

import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDate

@Stable
data class DateChooserState(
    val initialDate: LocalDate,
    val lastDay: LocalDate,
    val yearRange: IntRange,
    val title: String,
    val mode: ChooserMode,
) {
    enum class ChooserMode {
        Calendar,
        Text,
        ;
    }
}
