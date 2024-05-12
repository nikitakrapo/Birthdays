package com.nikitakrapo.birthdays.wizard.landing

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal object WizardLandingComponentPreview : WizardLandingComponent {

    override val state: StateFlow<WizardLandingState> = MutableStateFlow(
        WizardLandingState(
            username = "nikitakrapo",
            isBirthdayChosen = false,
            isCompletionAllowed = false,
        )
    )

    override fun onChooseBirthdayClicked() = Unit
}