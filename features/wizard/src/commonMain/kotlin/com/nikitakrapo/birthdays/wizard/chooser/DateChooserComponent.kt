package com.nikitakrapo.birthdays.wizard.chooser

import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

interface DateChooserComponent {

    val state: StateFlow<DateChooserState>

    fun onChooserModeSelected(mode: DateChooserState.ChooserMode)

    fun onDatePicked(date: LocalDate)
}
