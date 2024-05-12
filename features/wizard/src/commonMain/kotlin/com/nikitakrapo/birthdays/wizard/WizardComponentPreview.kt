package com.nikitakrapo.birthdays.wizard

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.birthdays.wizard.chooser.DateChooserComponentPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object WizardComponentPreview : WizardComponent {

    override val childStack: StateFlow<ChildStack<*, WizardComponent.WizardChild>> = MutableStateFlow(
        ChildStack(
            Child.Created(
                configuration = "Main",
                instance = WizardComponent.WizardChild.BirthdayChooser(DateChooserComponentPreview)
            )
        )
    )
}