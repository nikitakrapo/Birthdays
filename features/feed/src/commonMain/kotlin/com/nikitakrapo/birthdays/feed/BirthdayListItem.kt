package com.nikitakrapo.birthdays.feed

import com.nikitakrapo.birthdays.model.Birthday

sealed interface BirthdayListItem {

    data class HeaderItem(
        val text: String,
    ) : BirthdayListItem

    data class BirthdayItem(
        val birthday: Birthday,
    ) : BirthdayListItem
}