package com.nikitakrapo.birthdays.profile

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.navigate
import com.nikitakrapo.birthdays.account.AccountManager
import com.nikitakrapo.birthdays.chooser.DateChooserDialogComponentImpl
import com.nikitakrapo.birthdays.di.Di
import com.nikitakrapo.birthdays.model.ProfileInfo
import com.nikitakrapo.birthdays.platform.PlatformContext
import com.nikitakrapo.birthdays.platform.showToast
import com.nikitakrapo.birthdays.profile.ProfileEditComponent.ProfileEditDialog
import com.nikitakrapo.birthdays.repositories.profile.ProfileRepository
import com.nikitakrapo.birthdays.utils.decompose.asStateFlow
import com.nikitakrapo.birthdays.utils.decompose.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

class ProfileEditComponentImpl(
    componentContext: ComponentContext,
    initialProfileInfo: ProfileInfo,
    private val onProfileUpdated: (ProfileInfo) -> Unit,
    private val navigateBack: () -> Unit,
) : ProfileEditComponent, ComponentContext by componentContext {

    private val platformContext by Di.inject<PlatformContext>()
    private val accountManager by Di.inject<AccountManager>()
    private val profileRepository by Di.inject<ProfileRepository>()

    private val coroutineScope = coroutineScope()

    private val dialogNavigation = SlotNavigation<ProfileEditDialogConfig>()

    override val dialogSlot: StateFlow<ChildSlot<*, ProfileEditDialog>> = childSlot(
        key = "WizardSlot",
        source = dialogNavigation,
        serializer = ProfileEditDialogConfig.serializer(),
        initialConfiguration = { null },
        childFactory = ::dialogChild,
        handleBackButton = true,
    ).asStateFlow()

    private fun dialogChild(
        config: ProfileEditDialogConfig,
        componentContext: ComponentContext,
    ): ProfileEditDialog {
        return when (config) {
            is ProfileEditDialogConfig.BirthdayChooser -> ProfileEditDialog.BirthdayChooser(
                component = DateChooserDialogComponentImpl(
                    componentContext = componentContext,
                    initialDate = config.date,
                    onBirthdayConfirmed = { date ->
                        stateFlow.update {
                            it.copy(
                                birthday = date,
                                isConfirmAllowed = true,
                            )
                        }
                        dialogNavigation.navigate { null }
                    },
                    dismissDialog = { dialogNavigation.navigate { null } },
                )
            )
        }
    }
    private val stateFlow = MutableStateFlow(
        ProfileEditScreenState(
            username = initialProfileInfo.username,
            birthday = initialProfileInfo.birthday,
            isLoading = false,
            isConfirmAllowed = false,
        )
    )

    override val state = stateFlow.asStateFlow()

    override fun onUsernameChanged(username: String) {
        stateFlow.update {
            it.copy(
                username = username,
                isConfirmAllowed = true,
            )
        }
    }

    override fun onChangeBirthdayClicked() {
        val config = ProfileEditDialogConfig.BirthdayChooser(state.value.birthday)
        dialogNavigation.navigate { config }
    }

    override fun onConfirmClicked() {
        val uid = accountManager.user.value?.uid ?: return

        stateFlow.update { it.copy(isLoading = true) }
        coroutineScope.launch {
            val newInfo = ProfileInfo(
                username = state.value.username,
                birthday = state.value.birthday,
            )
            profileRepository.updateProfileInfo(uid, newInfo)
                .fold(
                    onSuccess = { onProfileUpdated(newInfo) },
                    onFailure = { showToast(platformContext, it.message ?: "Error") },
                )
        }
    }

    override fun onBackClicked() {
        navigateBack()
    }

    @Serializable
    private sealed interface ProfileEditDialogConfig {

        @Serializable
        data class BirthdayChooser(val date: LocalDate) : ProfileEditDialogConfig
    }
}