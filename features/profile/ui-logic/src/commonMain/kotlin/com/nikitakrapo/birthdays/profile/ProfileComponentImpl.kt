package com.nikitakrapo.birthdays.profile

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.birthdays.account.AccountManager
import com.nikitakrapo.birthdays.di.Di
import com.nikitakrapo.birthdays.model.ProfileInfo
import com.nikitakrapo.birthdays.repositories.profile.ProfileRepository
import com.nikitakrapo.birthdays.utils.coroutines.collectIn
import com.nikitakrapo.birthdays.utils.decompose.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileComponentImpl(
    componentContext: ComponentContext,
    private val navigateToProfileEdit: (ProfileInfo) -> Unit,
) : ProfileComponent, ComponentContext by componentContext {

    private val coroutineScope = coroutineScope()

    private val accountManager: AccountManager by Di.inject()
    private val profileRepository: ProfileRepository by Di.inject()

    private val stateFlow: MutableStateFlow<ProfileScreenState> =
        MutableStateFlow(ProfileScreenState.Loading)
    override val state = stateFlow.asStateFlow()

    private val showLogoutDialogFlow = MutableStateFlow(false)
    override val showLogoutDialog = showLogoutDialogFlow.asStateFlow()

    init {
        fetchUserData()
        observeUserData()
    }

    override fun onEditProfileClicked() {
        val profileInfo = (state.value as? ProfileScreenState.Loaded)?.profileInfo
        profileInfo?.let(navigateToProfileEdit)
    }

    override fun onLogoutClicked() {
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

    override fun onRetryClicked() {
        fetchUserData()
    }

    private fun fetchUserData() {
        coroutineScope.launch {
            val user = accountManager.user.value
            if (user != null) {
                val profileInfo = ProfileInfo(username = user.displayName, birthday = user.birthdayDate)
                stateFlow.value = ProfileScreenState.Loaded(profileInfo)
            } else {
                stateFlow.value = ProfileScreenState.Error
            }
        }
    }

    private fun observeUserData() {
        val uid = accountManager.user.value?.uid ?: return
        profileRepository.getProfileInfoFlow(uid)
            .collectIn(coroutineScope) { profileInfo ->
                stateFlow.value = ProfileScreenState.Loaded(profileInfo)
            }
    }
}