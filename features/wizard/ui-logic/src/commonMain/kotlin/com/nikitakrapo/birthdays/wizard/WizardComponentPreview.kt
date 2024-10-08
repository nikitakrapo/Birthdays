package com.nikitakrapo.birthdays.wizard

import com.arkivanov.decompose.router.slot.ChildSlot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object WizardComponentPreview : WizardComponent {

    override val state: StateFlow<WizardScreenState> = MutableStateFlow(
        WizardScreenState(
            screens = listOf(WizardScreenState.WizardScreen.Landing,
                WizardScreenState.WizardScreen.BirthdayChooser, WizardScreenState.WizardScreen.Final
            ),
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