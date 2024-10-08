package com.nikitakrapo.birthdays.wizard

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.navigate
import com.nikitakrapo.birthdays.chooser.DateChooserDialogComponentImpl
import com.nikitakrapo.birthdays.utils.decompose.asStateFlow
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
            screens = listOf(WizardScreenState.WizardScreen.Landing, WizardScreenState.WizardScreen.BirthdayChooser, WizardScreenState.WizardScreen.Final),
            lastAvailableScreen = 1,
            birthday = null,
        )
    )
    override val state: StateFlow<WizardScreenState> = stateFlow.asStateFlow()

    private val dialogNavigation = SlotNavigation<WizardDialogConfig>()

    override val dialogSlot: StateFlow<ChildSlot<*, WizardComponent.WizardDialog>> = childSlot(
        key = "WizardSlot",
        source = dialogNavigation,
        serializer = WizardDialogConfig.serializer(),
        initialConfiguration = { null },
        childFactory = ::dialogChild,
        handleBackButton = true,
    ).asStateFlow()

    private fun dialogChild(
        config: WizardDialogConfig,
        componentContext: ComponentContext,
    ): WizardComponent.WizardDialog {
        return when (config) {
            is WizardDialogConfig.BirthdayChooser -> WizardComponent.WizardDialog.BirthdayChooser(
                component = DateChooserDialogComponentImpl(
                    componentContext = componentContext,
                    initialDate = config.date,
                    onBirthdayConfirmed = { birthday ->
                        stateFlow.update {
                            it.copy(
                                birthday = birthday,
                                lastAvailableScreen = it.screens.lastIndex,
                            )
                        }
                        dialogNavigation.navigate { null }
                    },
                    dismissDialog = { dialogNavigation.navigate { null } },
                )
            )
        }
    }

    override fun onSelectBirthdayClicked() {
        dialogNavigation.navigate { WizardDialogConfig.BirthdayChooser(state.value.birthday) }
    }

    @Serializable
    private sealed interface WizardDialogConfig {

        @Serializable
        data class BirthdayChooser(val date: LocalDate?) : WizardDialogConfig
    }
}