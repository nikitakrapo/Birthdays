package com.nikitakrapo.birthdays.chooser

import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

interface DateChooserDialogComponent {

    val state: StateFlow<BirthdayChooserState>

    fun onBirthdayChosen(selectedDate: LocalDate)
    fun onDismiss()
    fun onBirthdayConfirmed()

    fun createDateChooserComponent(): DateChooserComponent
}