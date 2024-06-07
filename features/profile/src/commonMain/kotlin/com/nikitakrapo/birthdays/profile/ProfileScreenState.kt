package com.nikitakrapo.birthdays.profile

sealed interface ProfileScreenState {

    data object Loading : ProfileScreenState

    data class Loaded(
        val username: String,
        val birthday: String,
    ) : ProfileScreenState

    data object Error : ProfileScreenState
}