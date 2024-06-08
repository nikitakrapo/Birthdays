package com.nikitakrapo.birthdays.feed

import kotlinx.coroutines.flow.StateFlow

interface BirthdaysFeedComponent {

    val state: StateFlow<BirthdaysFeedScreenState>
}