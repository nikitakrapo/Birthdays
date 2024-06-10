package com.nikitakrapo.birthdays.repositories.birthdays

import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import kotlin.random.Random

internal class FakeBirthdaysRepository : BirthdaysRepository {

    override suspend fun getBirthdays(): Result<List<Birthday>> {
        delay(1000)
        val birthdays = List(30) {
            birthday()
        }
            .sortedBy { it.date }
        return Result.success(birthdays)
    }

    private fun birthday(): Birthday {
        return Birthday(
            id = Random.nextInt().toString(),
            date = birthdays.random(),
            title = "${names.random()} birthday",
            imageUrl = ""
        )
    }

    val names = listOf(
        "Alice Johnson",
        "Bob Smith",
        "Charlie Davis",
        "Diana Ross",
        "Ethan Brown",
        "Fiona Wilson",
        "George Clark",
        "Hannah Lewis",
        "Ian Thompson",
        "Jane Scott"
    )

    val birthdays = listOf(
        LocalDate(1990, 4, 15),
        LocalDate(1985, 6, 21),
        LocalDate(1992, 12, 3),
        LocalDate(1988, 9, 9),
        LocalDate(1995, 2, 27),
        LocalDate(1991, 11, 13),
        LocalDate(1987, 8, 30),
        LocalDate(1993, 7, 14),
        LocalDate(1989, 5, 24),
        LocalDate(1994, 10, 8)
    )
}
