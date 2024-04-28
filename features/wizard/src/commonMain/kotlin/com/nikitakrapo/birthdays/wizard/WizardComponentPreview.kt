package com.nikitakrapo.birthdays.wizard

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDateTime

object WizardComponentPreview : WizardComponent {

    private val startLocalDateTime = LocalDateTime(1900, 1, 1, 0, 0, 0, 0)
    private val endLocalDateTime = LocalDateTime(2024, 4, 26, 0, 0, 0, 0)

    override val state: StateFlow<WizardScreenState> = MutableStateFlow(
        WizardScreenState(
            selectedDate = endLocalDateTime,
            selectableDateTimes = startLocalDateTime to endLocalDateTime,
        )
    )
}