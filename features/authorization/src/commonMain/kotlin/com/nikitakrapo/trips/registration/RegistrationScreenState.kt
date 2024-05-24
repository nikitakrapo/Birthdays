package com.nikitakrapo.trips.registration

import kotlinx.datetime.LocalDate

data class RegistrationScreenState(
    val username: String,
    val email: String,
    val password: String,
    val birthday: LocalDate?,
    val isLoading: Boolean,
    val error: String?,
) {
    val isRegisterButtonActive: Boolean = username.isNotBlank()
            && email.isNotBlank()
            && password.isNotBlank()
            && birthday != null
            && error == null

    val isBirthdaySelected = birthday != null
}
