package com.nikitakrapo.birthdays.chooser

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

object DateChooserComponentPreview : DateChooserComponent {

    override val state: StateFlow<DateChooserState> = MutableStateFlow(
        DateChooserState(
            initialDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
            initialSelectedDate = null,
            startDate = LocalDate(1900, 1, 1),
            endDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
            title = "Choose your birthday",
            mode = DateChooserState.ChooserMode.Text,
        )
    )

    override fun onChooserModeSelected(mode: DateChooserState.ChooserMode) {
        (state as MutableStateFlow).update { it.copy(mode = mode) }
    }

    override fun onDatePicked(date: LocalDate) {
        (state as MutableStateFlow).update {
            val format = LocalDate.Format {
                dayOfMonth()
                char(' ')
                dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
                chars(", ")
                year()
            }
            it.copy(title = "Selected: ${date.format(format)}")
        }
    }
}