package com.nikitakrapo.birthdays.repositories.birthdays

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.datetime.LocalDate
import kotlin.random.Random

internal class FakeBirthdaysPagingSource : PagingSource<Int, Birthday>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Birthday> {
        val pageNumber = params.key ?: 0
        val prevKey = if (pageNumber > 0) pageNumber - 1 else null
        val nextKey = if (pageNumber < 10) pageNumber + 1 else null
        return LoadResult.Page(
            data = List(20) { birthday() },
            prevKey = prevKey,
            nextKey = nextKey,
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Birthday>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    private fun birthday(): Birthday {
        return Birthday(
            id = Random.nextInt().toString(),
            date = birthdays.random(),
            title = "${names.random()} birthday",
            imageUrl = ""
        )
    }

    private val names = listOf(
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

    private val birthdays = listOf(
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