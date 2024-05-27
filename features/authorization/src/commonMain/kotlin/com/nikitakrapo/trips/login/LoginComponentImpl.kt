package com.nikitakrapo.trips.login

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.birthdays.account.AccountManager
import com.nikitakrapo.birthdays.account.models.LoginResult
import com.nikitakrapo.trips.di.Di
import com.nikitakrapo.trips.utils.decompose.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class LoginComponentImpl(
    componentContext: ComponentContext,
    private val navigateToRegistration: () -> Unit,
) : LoginComponent, ComponentContext by componentContext {

    private val coroutineScope = coroutineScope()

    private val accountManager: AccountManager by Di.inject()

    private val stateFlow = MutableStateFlow(LoginScreenState(
        email = "",
        password = "",
        isLoading = false,
        error = null,
    ))
    override val state: StateFlow<LoginScreenState> = stateFlow.asStateFlow()

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
        navigateToRegistration()
    }

    override fun onDoneClicked() {
        stateFlow.update { it.copy(isLoading = true) }
        coroutineScope.launch {
            val loginResult = accountManager.login(state.value.email, state.value.password)
            when (loginResult) {
                is LoginResult.Success -> {
                    stateFlow.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                }
                is LoginResult.UnknownError -> {
                    val error = loginResult.error
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