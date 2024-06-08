package com.nikitakrapo.birthdays.repositories.profile

import com.nikitakrapo.birthdays.model.ProfileInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.datetime.LocalDate

class FakeProfileRepository : ProfileRepository {

    private val profileInfoFlow = MutableSharedFlow<ProfileInfo>()

    override suspend fun getProfileInfo(uid: String): Result<ProfileInfo> {
        delay(1000)
        return Result.success(
            ProfileInfo(
                username = "name|$uid",
                birthday = LocalDate(2000, 1, 1),
            )
        )
    }

    override fun getProfileInfoFlow(uid: String): Flow<ProfileInfo> {
        return profileInfoFlow
    }

    override suspend fun updateProfileInfo(uid: String, profileInfo: ProfileInfo): Result<Unit> {
        delay(1000)
        profileInfoFlow.emit(profileInfo)
        return Result.success(Unit)
    }
}