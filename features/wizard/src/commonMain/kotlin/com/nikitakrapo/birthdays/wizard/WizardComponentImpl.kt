package com.nikitakrapo.birthdays.wizard

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.navigate
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.nikitakrapo.birthdays.wizard.chooser.BirthdayChooserComponentImpl
import com.nikitakrapo.birthdays.wizard.landing.WizardLandingComponentImpl
import com.nikitakrapo.trips.utils.decompose.asStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

class WizardComponentImpl(
    componentContext: ComponentContext,
) : WizardComponent, ComponentContext by componentContext {

    private val stateFlow = MutableStateFlow(
        WizardScreenState(
            birthday = null,
        )
    )
    override val state: StateFlow<WizardScreenState> = stateFlow.asStateFlow()

    private val navigation = StackNavigation<WizardConfig>()

    override val childStack: StateFlow<ChildStack<*, WizardComponent.WizardChild>> = childStack(
        key = "WizardStack",
        source = navigation,
        serializer = WizardConfig.serializer(),
        initialStack = { listOf(WizardConfig.Landing) },
        childFactory = ::child,
        handleBackButton = true,
    ).asStateFlow()

    private val dialogNavigation = SlotNavigation<WizardDialogConfig>()

    override val dialogSlot: StateFlow<ChildSlot<*, WizardComponent.WizardDialog>> = childSlot(
        key = "WizardSlot",
        source = dialogNavigation,
        serializer = WizardDialogConfig.serializer(),
        initialConfiguration = { null },
        childFactory = ::dialogChild,
        handleBackButton = true,
    ).asStateFlow()

    private fun child(
        config: WizardConfig,
        componentContext: ComponentContext,
    ): WizardComponent.WizardChild {
        return when (config) {
            WizardConfig.Landing -> WizardComponent.WizardChild.Landing(
                component = WizardLandingComponentImpl(
                    componentContext = componentContext,
                    navigateToBirthdayChooser = {
                        val chooserConfig = WizardDialogConfig.BirthdayChooser(state.value.birthday)
                        dialogNavigation.navigate { chooserConfig }
                    }
                )
            )
        }
    }

    private fun dialogChild(
        config: WizardDialogConfig,
        componentContext: ComponentContext,
    ): WizardComponent.WizardDialog {
        return when (config) {
            is WizardDialogConfig.BirthdayChooser -> WizardComponent.WizardDialog.BirthdayChooser(
                component = BirthdayChooserComponentImpl(
                    componentContext = componentContext,
                    initialDate = config.date,
                    onBirthdayConfirmed = { birthday ->
                        stateFlow.update { it.copy(birthday = birthday) }
                        dialogNavigation.navigate { null }
                    },
                    dismissDialog = { dialogNavigation.navigate { null } },
                )
            )
        }
    }

    @Serializable
    private sealed interface WizardConfig {

        @Serializable
        data object Landing : WizardConfig
    }

    @Serializable
    private sealed interface WizardDialogConfig {

        @Serializable
        data class BirthdayChooser(val date: LocalDate?) : WizardDialogConfig
    }
}