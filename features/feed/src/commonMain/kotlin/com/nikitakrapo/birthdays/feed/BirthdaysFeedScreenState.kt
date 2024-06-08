package com.nikitakrapo.birthdays.feed

sealed interface BirthdaysFeedScreenState {

    data object Loading : BirthdaysFeedScreenState
}