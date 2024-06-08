package com.nikitakrapo.birthdays.repositories.profile

import com.nikitakrapo.birthdays.model.ProfileInfo
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getProfileInfo(uid: String): Result<ProfileInfo>

    fun getProfileInfoFlow(uid: String): Flow<ProfileInfo>

    suspend fun updateProfileInfo(
        uid: String,
        profileInfo: ProfileInfo,
    ): Result<Unit>
}