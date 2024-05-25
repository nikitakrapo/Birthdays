package com.nikitakrapo.trips.profile

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.birthdays.account.AccountManager
import com.nikitakrapo.trips.di.Di
import com.nikitakrapo.trips.utils.decompose.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileComponentImpl(
    componentContext: ComponentContext,
) : ProfileComponent, ComponentContext by componentContext {

    private val coroutineScope = coroutineScope()

    private val accountManager: AccountManager by Di.inject()

    private val stateFlow = MutableStateFlow(
        ProfileScreenState(
            username = accountManager.account.value
                ?.let { it.email ?: "" }
                ?: "",
            showLogoutDialog = false,
        )
    )
    override val state: StateFlow<ProfileScreenState> = stateFlow.asStateFlow()

    override fun onSettingsClick() {
        TODO("Not yet implemented")
    }

    override fun onLogoutClick() {
        stateFlow.update { it.copy(showLogoutDialog = true) }
    }

    override fun onLogoutConfirmed() {
        stateFlow.update { it.copy(showLogoutDialog = false) }
        coroutineScope.launch {
            accountManager.logout()
        }
    }

    override fun onLogoutCancelled() {
        stateFlow.update { it.copy(showLogoutDialog = false) }
    }
}