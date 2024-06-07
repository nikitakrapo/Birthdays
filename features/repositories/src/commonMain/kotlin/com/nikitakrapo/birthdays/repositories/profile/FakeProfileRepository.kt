package com.nikitakrapo.birthdays.repositories.profile

import com.nikitakrapo.birthdays.model.ProfileInfo
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate

class FakeProfileRepository : ProfileRepository {

    override suspend fun getProfileInfo(uid: String): Result<ProfileInfo> {
        delay(1000)
        return Result.success(
            ProfileInfo(
                username = "name|$uid",
                birthday = LocalDate(2000, 1, 1),
            )
        )
    }
}