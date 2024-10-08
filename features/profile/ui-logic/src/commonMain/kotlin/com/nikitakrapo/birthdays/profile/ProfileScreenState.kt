package com.nikitakrapo.birthdays.profile

import com.nikitakrapo.birthdays.model.ProfileInfo

sealed interface ProfileScreenState {

    data object Loading : ProfileScreenState

    data class Loaded(
        val profileInfo: ProfileInfo,
    ) : ProfileScreenState

    data object Error : ProfileScreenState
}
