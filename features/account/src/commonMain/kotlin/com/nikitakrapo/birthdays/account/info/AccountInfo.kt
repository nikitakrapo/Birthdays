package com.nikitakrapo.birthdays.account.info

import kotlinx.datetime.LocalDate

data class AccountInfo(
    val username: String,
    val birthday: LocalDate,
)
