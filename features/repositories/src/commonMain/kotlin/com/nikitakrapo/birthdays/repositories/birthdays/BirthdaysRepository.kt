package com.nikitakrapo.birthdays.repositories.birthdays

import com.nikitakrapo.birthdays.model.Birthday

interface BirthdaysRepository {

    // TODO: add date range
    suspend fun getBirthdays(): Result<List<Birthday>>
}