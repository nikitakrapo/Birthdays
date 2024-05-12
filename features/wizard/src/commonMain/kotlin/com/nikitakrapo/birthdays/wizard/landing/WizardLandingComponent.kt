package com.nikitakrapo.birthdays.wizard.landing

import kotlinx.coroutines.flow.StateFlow

internal interface WizardLandingComponent {

    val state: StateFlow<WizardLandingState>

    fun onChooseBirthdayClicked()
}