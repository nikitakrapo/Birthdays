package com.nikitakrapo.birthdays.wizard.chooser

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal class BirthdayChooserComponentImpl(
    componentContext: ComponentContext,
    private val onBirthdayConfirmed: (LocalDate) -> Unit,
    private val dismissDialog: () -> Unit,
) : BirthdayChooserComponent, ComponentContext by componentContext {

    private val stateFlow = MutableStateFlow(
        BirthdayChooserState(
            chosenDate = null,
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
        return DateChooserComponentImpl(onDateUpdated = ::onBirthdayChosen)
    }
}