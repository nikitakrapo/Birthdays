package com.nikitakrapo.birthdays.model

import kotlinx.datetime.LocalDate

class Birthday(
    val id: String,
    val title: String,
    val date: LocalDate,
    val imageUrl: String?,
) {

    companion object {

        fun forPreview() = Birthday(
            id = "123",
            date = LocalDate(2000, 1, 1),
            title = "Birthday",
            imageUrl = "",
        )
    }
}
