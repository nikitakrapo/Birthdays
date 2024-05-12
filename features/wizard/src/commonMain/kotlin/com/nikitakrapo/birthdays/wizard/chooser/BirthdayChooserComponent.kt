package com.nikitakrapo.birthdays.wizard.chooser

import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

internal interface BirthdayChooserComponent {

    val state: StateFlow<BirthdayChooserState>

    fun onBirthdayChosen(selectedDate: LocalDate)
    fun onDismiss()
    fun onBirthdayConfirmed()

    fun createDateChooserComponent(): DateChooserComponent
}