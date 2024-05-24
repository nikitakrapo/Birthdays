package com.nikitakrapo.birthdays.wizard

import com.arkivanov.decompose.router.slot.ChildSlot
import com.nikitakrapo.birthdays.wizard.chooser.BirthdayChooserComponent
import kotlinx.coroutines.flow.StateFlow

interface WizardComponent {

    val state: StateFlow<WizardScreenState>

    val dialogSlot: StateFlow<ChildSlot<*, WizardDialog>>

    fun onSelectBirthdayClicked()

    sealed interface WizardDialog {

        data class BirthdayChooser internal constructor(
            internal val component: BirthdayChooserComponent,
        ) : WizardDialog
    }
}