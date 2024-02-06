package com.nikitakrapo.trips

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.trips.account.AccountManager
import com.nikitakrapo.trips.di.Di

class AuthorizationComponentImpl(
    componentContext: ComponentContext,
) : AuthorizationComponent, ComponentContext by componentContext {

    private val accountManager: AccountManager by Di.inject()

    override fun onEmailLoginClicked() {
        TODO("Not yet implemented")
    }

    override fun onRegisterClicked() {
        TODO("Not yet implemented")
    }
}