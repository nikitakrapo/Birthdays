package com.nikitakrapo.birthdays.wizard

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.birthdays.wizard.chooser.BirthdayChooserComponent
import com.nikitakrapo.birthdays.wizard.landing.WizardLandingComponent
import kotlinx.coroutines.flow.StateFlow

interface WizardComponent {

    val childStack: StateFlow<ChildStack<*, WizardChild>>

    val dialogSlot: StateFlow<ChildSlot<*, WizardDialog>>

    sealed interface WizardChild {

        data class Landing internal constructor(
            internal val component: WizardLandingComponent,
        ) : WizardChild
    }

    sealed interface WizardDialog {

        data class BirthdayChooser internal constructor(
            internal val component: BirthdayChooserComponent,
        ) : WizardDialog
    }
}