package com.nikitakrapo.birthdays.wizard

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

object WizardComponentPreview : WizardComponent {

    private val endLocalDateTime = LocalDate(2024, 4, 26)

    override val state: StateFlow<WizardScreenState> = MutableStateFlow(
        WizardScreenState(
            initialDate = endLocalDateTime,
            yearRange = 1900..2024,
        )
    )
}