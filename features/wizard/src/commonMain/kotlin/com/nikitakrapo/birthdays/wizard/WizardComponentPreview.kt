package com.nikitakrapo.birthdays.wizard

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.birthdays.wizard.landing.WizardLandingComponentPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object WizardComponentPreview : WizardComponent {

    override val childStack: StateFlow<ChildStack<*, WizardComponent.WizardChild>> = MutableStateFlow(
        ChildStack(
            Child.Created(
                configuration = "Main",
                instance = WizardComponent.WizardChild.Landing(component = WizardLandingComponentPreview),
            )
        )
    )

    override val dialogSlot: StateFlow<ChildSlot<*, WizardComponent.WizardDialog>> = MutableStateFlow(
        ChildSlot<Any, WizardComponent.WizardDialog>(
            null,
        )
    )
}