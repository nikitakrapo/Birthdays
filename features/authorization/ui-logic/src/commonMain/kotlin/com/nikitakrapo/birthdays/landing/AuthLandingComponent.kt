package com.nikitakrapo.birthdays.landing

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow

interface AuthLandingComponent {

    val stateValue: Value<LandingScreenState>

    val state: StateFlow<LandingScreenState>

    fun onLoginClicked()
}