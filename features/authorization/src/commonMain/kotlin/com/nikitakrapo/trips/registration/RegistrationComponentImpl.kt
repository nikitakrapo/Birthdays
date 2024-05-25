package com.nikitakrapo.trips.registration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.navigate
import com.nikitakrapo.birthdays.account.AccountManager
import com.nikitakrapo.birthdays.account.RegistrationResult
import com.nikitakrapo.birthdays.chooser.DateChooserDialogComponentImpl
import com.nikitakrapo.trips.di.Di
import com.nikitakrapo.trips.utils.decompose.asStateFlow
import com.nikitakrapo.trips.utils.decompose.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

internal class RegistrationComponentImpl(
    componentContext: ComponentContext,
    private val navigateBack: () -> Unit,
): RegistrationComponent, ComponentContext by componentContext {

    private val coroutineScope = coroutineScope()

    private val accountManager: AccountManager by Di.inject()

    private val dialogNavigation = SlotNavigation<RegistrationDialogConfig>()

    override val dialogSlot: StateFlow<ChildSlot<*, RegistrationComponent.RegistrationDialog>> = childSlot(
        key = "WizardSlot",
        source = dialogNavigation,
        serializer = RegistrationDialogConfig.serializer(),
        initialConfiguration = { null },
        childFactory = ::dialogChild,
        handleBackButton = true,
    ).asStateFlow()

    private fun dialogChild(
        config: RegistrationDialogConfig,
        componentContext: ComponentContext,
    ): RegistrationComponent.RegistrationDialog {
        return when (config) {
            is RegistrationDialogConfig.BirthdayChooser -> RegistrationComponent.RegistrationDialog.BirthdayChooser(
                component = DateChooserDialogComponentImpl(
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

    private val stateFlow = MutableStateFlow(RegistrationScreenState(
        username = "",
        email = "",
        password = "",
        birthday = null,
        isLoading = false,
        error = null,
    ))
    override val state: StateFlow<RegistrationScreenState> = stateFlow.asStateFlow()

    override fun onUsernameTextChanged(text: String) {
        stateFlow.update {
            it.copy(
                username = text,
                error = null,
            )
        }
    }

    override fun onEmailTextChanged(text: String) {
        stateFlow.update {
            it.copy(
                email = text,
                error = null,
            )
        }
    }

    override fun onPasswordTextChanged(text: String) {
        stateFlow.update {
            it.copy(
                password = text,
                error = null,
            )
        }
    }

    override fun onSelectBirthdayClicked() {
        val config = RegistrationDialogConfig.BirthdayChooser(state.value.birthday)
        dialogNavigation.navigate { config }
    }

    override fun onRegisterClicked() {
        val birthday = state.value.birthday ?: return
        stateFlow.update { it.copy(isLoading = true) }
        coroutineScope.launch(Dispatchers.IO) {
            val registerResult = accountManager.register(
                username = state.value.username,
                email = state.value.email,
                birthday = birthday,
                password = state.value.password,
            )
            withContext(Dispatchers.Main) {
                when (registerResult) {
                    is RegistrationResult.Success -> {
                        stateFlow.update {
                            it.copy(
                                isLoading = false,
                            )
                        }
                    }
                    is RegistrationResult.UnknownError -> {
                        val error = registerResult.error
                        stateFlow.update {
                            it.copy(
                                isLoading = false,
                                error = error,
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onBackClicked() {
        navigateBack()
    }

    @Serializable
    private sealed interface RegistrationDialogConfig {

        @Serializable
        data class BirthdayChooser(val date: LocalDate?) : RegistrationDialogConfig
    }

}