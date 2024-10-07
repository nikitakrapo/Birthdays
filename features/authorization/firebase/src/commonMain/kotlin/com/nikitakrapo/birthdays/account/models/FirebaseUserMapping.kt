package com.nikitakrapo.birthdays.account.models

import dev.gitlive.firebase.auth.FirebaseUser

internal fun FirebaseUser.toUser(): User {
    return User(
        uid = uid,
    )
}