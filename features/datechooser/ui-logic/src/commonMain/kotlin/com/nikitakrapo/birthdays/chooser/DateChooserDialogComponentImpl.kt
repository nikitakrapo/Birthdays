package com.nikitakrapo.birthdays.chooser

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateChooserDialogComponentImpl(
    componentContext: ComponentContext,
    private val initialDate: LocalDate?,
    private val onBirthdayConfirmed: (LocalDate) -> Unit,
    private val dismissDialog: () -> Unit,
) : DateChooserDialogComponent, ComponentContext by componentContext {

    private val stateFlow = MutableStateFlow(
        BirthdayChooserState(
            chosenDate = initialDate,
            yearRange = 1900..Clock.System.now().toLocalDateTime(TimeZone.UTC).year,
        )
    )
    override val state: StateFlow<BirthdayChooserState> = stateFlow.asStateFlow()

    override fun onBirthdayChosen(selectedDate: LocalDate) {
        stateFlow.update { it.copy(chosenDate = selectedDate) }
    }

    override fun onBirthdayConfirmed() {
        val selectedBirthday = stateFlow.value.chosenDate
        selectedBirthday?.let(onBirthdayConfirmed)
    }

    override fun onDismiss() {
        dismissDialog()
    }

    override fun createDateChooserComponent(): DateChooserComponent {
        return DateChooserComponentImpl(
            initialSelectedDate = initialDate,
            onDateUpdated = ::onBirthdayChosen,
        )
    }
}