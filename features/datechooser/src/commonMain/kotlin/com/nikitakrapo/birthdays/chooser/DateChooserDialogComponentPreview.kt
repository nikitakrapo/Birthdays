package com.nikitakrapo.birthdays.chooser

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

internal object DateChooserDialogComponentPreview : DateChooserDialogComponent {

    override val state: StateFlow<BirthdayChooserState> = MutableStateFlow(
        BirthdayChooserState(
            chosenDate = LocalDate(2024, 1, 1),
            yearRange = 1900..2024,
        )
    )

    override fun onBirthdayChosen(selectedDate: LocalDate) = Unit
    override fun onBirthdayConfirmed() = Unit

    override fun onDismiss() = Unit

    override fun createDateChooserComponent() = DateChooserComponentPreview
}