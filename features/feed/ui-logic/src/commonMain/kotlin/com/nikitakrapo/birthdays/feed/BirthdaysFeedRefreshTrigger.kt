package com.nikitakrapo.birthdays.feed

import kotlinx.coroutines.flow.MutableSharedFlow

class BirthdaysFeedRefreshTrigger {

    val triggers = MutableSharedFlow<Unit>()

    fun triggerRefresh() {
        triggers.tryEmit(Unit)
    }
}