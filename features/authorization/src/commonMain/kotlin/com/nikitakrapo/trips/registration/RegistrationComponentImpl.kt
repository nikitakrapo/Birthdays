package com.nikitakrapo.trips.registration

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.trips.account.AccountManager
import com.nikitakrapo.trips.account.RegistrationResult
import com.nikitakrapo.trips.di.Di
import com.nikitakrapo.trips.utils.decompose.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class RegistrationComponentImpl(
    componentContext: ComponentContext,
    private val navigateBack: () -> Unit,
): RegistrationComponent, ComponentContext by componentContext {

    private val coroutineScope = coroutineScope()

    private val accountManager: AccountManager by Di.inject()

    private val stateFlow = MutableStateFlow(RegistrationScreenState(
        email = "",
        password = "",
        isLoading = false,
        error = null,
    ))
    override val state: StateFlow<RegistrationScreenState> = stateFlow.asStateFlow()

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

    override fun onRegisterClicked() {
        stateFlow.update { it.copy(isLoading = true) }
        coroutineScope.launch {
            val registerResult = accountManager.register(state.value.email, state.value.password)
            when (registerResult) {
                is RegistrationResult.Success -> {
                    stateFlow.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                }
                is RegistrationResult.UnknownError -> {
                    val error = registerResult.error.message ?: "Unknown error"
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

    override fun onBackClicked() {
        navigateBack()
    }
}