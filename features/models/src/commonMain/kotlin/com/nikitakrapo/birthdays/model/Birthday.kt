package com.nikitakrapo.birthdays.model

import kotlinx.datetime.LocalDate

class Birthday(
    val id: String,
    val title: String,
    val date: LocalDate,
    val imageUrl: String?,
)
