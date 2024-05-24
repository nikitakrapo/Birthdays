package com.nikitakrapo.birthdays.wizard

import com.arkivanov.decompose.router.slot.ChildSlot
import com.nikitakrapo.birthdays.wizard.WizardScreenState.WizardScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object WizardComponentPreview : WizardComponent {

    override val state: StateFlow<WizardScreenState> = MutableStateFlow(
        WizardScreenState(
            screens = listOf(WizardScreen.Landing, WizardScreen.BirthdayChooser, WizardScreen.Final),
            lastAvailableScreen = 1,
            birthday = null,
        )
    )

    override val dialogSlot: StateFlow<ChildSlot<*, WizardComponent.WizardDialog>> = MutableStateFlow(
        ChildSlot<Any, WizardComponent.WizardDialog>(
            null,
        )
    )

    override fun onSelectBirthdayClicked() = Unit
}