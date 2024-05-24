package com.nikitakrapo.trips.registration

import com.arkivanov.decompose.router.slot.ChildSlot
import com.nikitakrapo.birthdays.chooser.DateChooserDialogComponent
import kotlinx.coroutines.flow.StateFlow

interface RegistrationComponent {

    val dialogSlot: StateFlow<ChildSlot<*, RegistrationDialog>>

    val state: StateFlow<RegistrationScreenState>

    fun onUsernameTextChanged(text: String)
    fun onEmailTextChanged(text: String)
    fun onPasswordTextChanged(text: String)
    fun onSelectBirthdayClicked()
    fun onRegisterClicked()
    fun onBackClicked()

    sealed interface RegistrationDialog {

        data class BirthdayChooser internal constructor(
            internal val component: DateChooserDialogComponent,
        ) : RegistrationDialog
    }
}