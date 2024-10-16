package com.nikitakrapo.birthdays.landing

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.birthdays.account.AccountManager
import com.nikitakrapo.birthdays.account.result.LoginResult
import com.nikitakrapo.birthdays.di.Di
import com.nikitakrapo.birthdays.utils.decompose.asValue
import com.nikitakrapo.birthdays.utils.decompose.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthLandingComponentImpl(
    private val navigateToLogin: () -> Unit,
    componentContext: ComponentContext,
) : AuthLandingComponent, ComponentContext by componentContext {

    private val coroutineScope = coroutineScope()

    private val accountManager: AccountManager by Di.inject()

    private val stateFlow: MutableStateFlow<LandingScreenState> = MutableStateFlow(
        LandingScreenState(
            error = null,
        )
    )
    override val stateValue = stateFlow.asValue()
    override val state = stateFlow.asStateFlow()

    override fun onLoginClicked() {
        navigateToLogin()
    }

    override fun onSkipClicked() {
        coroutineScope.launch {
            when (val result = accountManager.registerAnonymously()) {
                is LoginResult.Success -> {}
                is LoginResult.UnknownError -> {
                    stateFlow.value = stateFlow.value.copy(error = result.error ?: "Error")
                }
            }
        }
    }
}