package com.nikitakrapo.birthdays.wizard

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.nikitakrapo.birthdays.wizard.chooser.DateChooserComponentPreview
import com.nikitakrapo.trips.utils.decompose.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

class WizardComponentImpl(
    componentContext: ComponentContext,
) : WizardComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<WizardConfig>()

    override val childStack: StateFlow<ChildStack<*, WizardComponent.WizardChild>> = childStack(
        key = "MainStack",
        source = navigation,
        serializer = WizardConfig.serializer(),
        initialStack = { listOf(WizardConfig.Landing) },
        childFactory = ::child,
    ).asStateFlow()

    private fun child(
        config: WizardConfig,
        componentContext: ComponentContext,
    ): WizardComponent.WizardChild {
        return when (config) {
            WizardConfig.Landing -> WizardComponent.WizardChild.Landing

            WizardConfig.BirthdayChooser -> WizardComponent.WizardChild.BirthdayChooser(
                component = DateChooserComponentPreview,
            )
        }
    }

    @Serializable
    private sealed interface WizardConfig {

        @Serializable
        data object Landing : WizardConfig

        @Serializable
        data object BirthdayChooser : WizardConfig
    }
}