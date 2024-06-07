package com.nikitakrapo.birthdays.profile

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.birthdays.account.AccountManager
import com.nikitakrapo.birthdays.di.Di
import com.nikitakrapo.birthdays.repositories.profile.ProfileRepository
import com.nikitakrapo.birthdays.utils.decompose.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileComponentImpl(
    componentContext: ComponentContext,
) : ProfileComponent, ComponentContext by componentContext {

    private val coroutineScope = coroutineScope()

    private val accountManager: AccountManager by Di.inject()
    private val profileRepository: ProfileRepository by Di.inject()

    private val stateFlow: MutableStateFlow<ProfileScreenState> = MutableStateFlow(ProfileScreenState.Loading)
    override val state = stateFlow.asStateFlow()

    private val showLogoutDialogFlow = MutableStateFlow(false)
    override val showLogoutDialog = showLogoutDialogFlow.asStateFlow()

    init {
        fetchUserData()
    }

    override fun onLogoutClick() {
        showLogoutDialogFlow.value = true
    }

    override fun onLogoutConfirmed() {
        showLogoutDialogFlow.value = false
        coroutineScope.launch {
            accountManager.logout()
        }
    }

    override fun onLogoutCancelled() {
        showLogoutDialogFlow.value = false
    }

    override fun onRefreshClicked() {
        fetchUserData()
    }

    private fun fetchUserData() {
        coroutineScope.launch {
            val uid = accountManager.account.value?.uid
            val accountInfo = uid?.let { profileRepository.getProfileInfo(it) }?.getOrNull()
            if (accountInfo != null) {
                stateFlow.value = ProfileScreenState.Loaded(
                    username = accountInfo.username,
                    birthday = accountInfo.birthday.toString(),
                )
            } else {
                stateFlow.value = ProfileScreenState.Error
            }
        }
    }
}