package com.nikitakrapo.birthdays.wizard

import com.arkivanov.decompose.router.slot.ChildSlot
import com.nikitakrapo.birthdays.chooser.DateChooserDialogComponent
import kotlinx.coroutines.flow.StateFlow

interface WizardComponent {

    val state: StateFlow<WizardScreenState>

    val dialogSlot: StateFlow<ChildSlot<*, WizardDialog>>

    fun onSelectBirthdayClicked()

    sealed interface WizardDialog {

        data class BirthdayChooser(
            val component: DateChooserDialogComponent,
        ) : WizardDialog
    }
}