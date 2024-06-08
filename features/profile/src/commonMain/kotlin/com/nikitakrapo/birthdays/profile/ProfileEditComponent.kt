package com.nikitakrapo.birthdays.profile

import com.arkivanov.decompose.router.slot.ChildSlot
import com.nikitakrapo.birthdays.chooser.DateChooserDialogComponent
import kotlinx.coroutines.flow.StateFlow

interface ProfileEditComponent {

    val dialogSlot: StateFlow<ChildSlot<*, ProfileEditDialog>>

    val state: StateFlow<ProfileEditScreenState>

    fun onUsernameChanged(username: String)
    fun onChangeBirthdayClicked()
    fun onConfirmClicked()
    fun onBackClicked()

    sealed interface ProfileEditDialog {

        data class BirthdayChooser internal constructor(
            internal val component: DateChooserDialogComponent,
        ) : ProfileEditDialog
    }
}