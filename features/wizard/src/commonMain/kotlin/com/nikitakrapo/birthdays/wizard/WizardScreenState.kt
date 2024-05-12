package com.nikitakrapo.birthdays.wizard

sealed interface WizardScreenState {

    data object Landing : WizardScreenState

    data class BirthdayChoosing(
        val isYearSpecified: Boolean,
    ) : WizardScreenState
}
