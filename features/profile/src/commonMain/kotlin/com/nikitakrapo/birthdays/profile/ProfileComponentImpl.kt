package com.nikitakrapo.birthdays.profile

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.birthdays.account.AccountManager
import com.nikitakrapo.birthdays.account.info.AccountInfoRepository
import com.nikitakrapo.birthdays.di.Di
import com.nikitakrapo.birthdays.utils.decompose.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileComponentImpl(
    componentContext: ComponentContext,
) : ProfileComponent, ComponentContext by componentContext {

    private val coroutineScope = coroutineScope()

    private val accountManager: AccountManager by Di.inject()
    private val accountInfoRepository: AccountInfoRepository by Di.inject()

    private val stateFlow = MutableStateFlow(
        ProfileScreenState(
            username = "",
            birthday = "",
            isLoading = true,
            isError = false,
            showLogoutDialog = false,
        )
    )
    override val state: StateFlow<ProfileScreenState> = stateFlow.asStateFlow()

    init {
        fetchUserData()
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

    override fun onRefreshClicked() {
        fetchUserData()
    }

    private fun fetchUserData() {
        coroutineScope.launch(Dispatchers.IO) {
            val uid = accountManager.account.value?.uid
            val accountInfo = uid?.let { accountInfoRepository.getAccountInfo(it) }
            accountInfo?.getOrNull()?.let { info ->
                withContext(Dispatchers.Main) {
                    stateFlow.update {
                        it.copy(
                            username = info.username,
                            birthday = info.birthday.toString(),
                            isLoading = false,
                        )
                    }
                }
            } ?: run {
                withContext(Dispatchers.Main) {
                    stateFlow.update { it.copy(isError = true) }
                }
            }
        }
    }
}