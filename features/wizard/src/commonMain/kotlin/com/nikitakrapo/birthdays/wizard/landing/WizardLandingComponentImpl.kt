package com.nikitakrapo.birthdays.wizard.landing

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class WizardLandingComponentImpl(
    componentContext: ComponentContext,
    private val navigateToBirthdayChooser: () -> Unit,
) : WizardLandingComponent, ComponentContext by componentContext {

    private val stateFlow = MutableStateFlow(
        WizardLandingState(
            username = "nikitakrapo",
            isBirthdayChosen = false,
            isCompletionAllowed = false,
        )
    )

    override val state: StateFlow<WizardLandingState> = stateFlow.asStateFlow()

    override fun onChooseBirthdayClicked() {
        navigateToBirthdayChooser()
    }
}