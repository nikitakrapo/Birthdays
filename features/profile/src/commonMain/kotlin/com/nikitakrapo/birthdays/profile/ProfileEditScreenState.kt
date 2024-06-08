package com.nikitakrapo.birthdays.profile

import kotlinx.datetime.LocalDate

data class ProfileEditScreenState(
    val username: String,
    val birthday: LocalDate,
    val isLoading: Boolean,
    val isConfirmAllowed: Boolean,
)
