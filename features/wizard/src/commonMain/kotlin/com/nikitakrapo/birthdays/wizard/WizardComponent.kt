package com.nikitakrapo.birthdays.wizard

import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.birthdays.wizard.chooser.DateChooserComponent
import kotlinx.coroutines.flow.StateFlow

interface WizardComponent {

    val childStack: StateFlow<ChildStack<*, WizardChild>>

    sealed interface WizardChild {

        data object Landing : WizardChild

        data class BirthdayChooser(
            val component: DateChooserComponent,
        ) : WizardChild
    }
}