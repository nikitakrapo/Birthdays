package com.nikitakrapo.birthdays.wizard.chooser

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

class DateChooserComponentImpl(
    initialSelectedDate: LocalDate?,
    private val onDateUpdated: (LocalDate) -> Unit,
) : DateChooserComponent {

    private val stateFlow = MutableStateFlow(
        DateChooserState(
            initialDate = initialSelectedDate ?: Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
            initialSelectedDate = initialSelectedDate,
            startDate = LocalDate(1900, 1, 1),
            endDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
            title = "Choose your birthday",
            mode = DateChooserState.ChooserMode.Calendar,
        )
    )
    override val state: StateFlow<DateChooserState> = stateFlow.asStateFlow()

    override fun onChooserModeSelected(mode: DateChooserState.ChooserMode) {
        stateFlow.update { it.copy(mode = mode) }
    }

    override fun onDatePicked(date: LocalDate) {
        stateFlow.update {
            val format = LocalDate.Format {
                dayOfMonth()
                char(' ')
                dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
                chars(", ")
                year()
            }
            it.copy(title = "Selected: ${date.format(format)}")
        }
        onDateUpdated(date)
    }
}
