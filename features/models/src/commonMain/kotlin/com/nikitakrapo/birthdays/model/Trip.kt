package com.nikitakrapo.birthdays.model

import kotlinx.datetime.Instant

data class Trip(
    val id: String,
    val title: String,
    val startDatetime: Instant,
    val endDatetime: Instant,
)
