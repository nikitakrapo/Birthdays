package com.nikitakrapo.birthdays.feed

import com.nikitakrapo.birthdays.model.Birthday

sealed interface BirthdayFeedListItem {

    data class HeaderItemFeed(
        val text: String,
    ) : BirthdayFeedListItem

    data class BirthdayItem(
        val birthday: Birthday,
    ) : BirthdayFeedListItem
}