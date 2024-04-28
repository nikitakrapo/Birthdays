package com.nikitakrapo.birthdays.wizard.chooser

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object DateChooserComponentPreview : DateChooserComponent {

    override val state: StateFlow<DateChooserState> = MutableStateFlow(
        DateChooserState(
            initialDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
            lastDay = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
            yearRange = 1900..2024,
            title = "Choose your birthday",
            mode = DateChooserState.ChooserMode.Text,
        )
    )

    override fun onChooserModeSelected(mode: DateChooserState.ChooserMode) {
        (state as MutableStateFlow).update { it.copy(mode = mode) }
    }

    override fun onDatePicked(date: LocalDate) = Unit
}