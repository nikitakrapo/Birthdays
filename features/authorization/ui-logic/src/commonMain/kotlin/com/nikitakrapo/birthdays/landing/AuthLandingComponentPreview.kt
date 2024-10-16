package com.nikitakrapo.birthdays.landing

import com.arkivanov.decompose.value.MutableValue
import kotlinx.coroutines.flow.MutableStateFlow

object AuthLandingComponentPreview : AuthLandingComponent {

    private val stateInstance = LandingScreenState(
        error = null,
    )

    override val stateValue = MutableValue(stateInstance)
    override val state = MutableStateFlow(stateInstance)

    override fun onLoginClicked() = Unit
    override fun onSkipClicked() = Unit
}