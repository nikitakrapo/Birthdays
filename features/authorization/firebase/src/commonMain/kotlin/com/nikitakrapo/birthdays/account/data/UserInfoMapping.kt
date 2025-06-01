package com.nikitakrapo.birthdays.account.data

import com.nikitakrapo.birthdays.account.models.User
import com.nikitakrapo.birthdays.repositories.user.UserInfo

internal fun UserInfo.toUser(): User {
    return User(
        uid = uid,
        displayName = displayName,
        birthdayDate = birthdayDate,
    )
}