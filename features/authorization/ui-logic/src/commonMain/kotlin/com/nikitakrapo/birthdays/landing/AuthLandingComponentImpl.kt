package com.nikitakrapo.birthdays.landing

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.birthdays.utils.decompose.asValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthLandingComponentImpl(
    private val navigateToLogin: () -> Unit,
    componentContext: ComponentContext,
) : AuthLandingComponent, ComponentContext by componentContext {

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
}