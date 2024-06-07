package com.nikitakrapo.birthdays.repositories.profile

import com.nikitakrapo.birthdays.model.ProfileInfo

interface ProfileRepository {

    suspend fun getProfileInfo(uid: String): Result<ProfileInfo>
}