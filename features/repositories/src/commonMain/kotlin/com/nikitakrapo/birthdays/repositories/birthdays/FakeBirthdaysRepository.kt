package com.nikitakrapo.birthdays.repositories.birthdays

import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import java.time.Month

internal class FakeBirthdaysRepository : BirthdaysRepository {

    override suspend fun getBirthdays(): Result<List<Birthday>> {
        delay(1000)
        val birthdays = listOf(
            Birthday(date = LocalDate(2024, Month.JUNE, 2)),
            Birthday(date = LocalDate(2024, Month.JUNE, 5)),
            Birthday(date = LocalDate(2024, Month.JUNE, 6)),
            Birthday(date = LocalDate(2024, Month.JUNE, 9)),
            Birthday(date = LocalDate(2024, Month.JUNE, 20)),
            Birthday(date = LocalDate(2024, Month.JULY, 1)),
            Birthday(date = LocalDate(2024, Month.JULY, 3)),
            Birthday(date = LocalDate(2024, Month.JULY, 6)),
            Birthday(date = LocalDate(2024, Month.JULY, 7)),
            Birthday(date = LocalDate(2024, Month.JULY, 15)),
            Birthday(date = LocalDate(2024, Month.JULY, 20)),
        )
        return Result.success(birthdays)
    }
}
