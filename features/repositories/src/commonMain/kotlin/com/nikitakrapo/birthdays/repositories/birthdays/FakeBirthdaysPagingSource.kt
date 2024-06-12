package com.nikitakrapo.birthdays.repositories.birthdays

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import kotlin.random.Random

internal class FakeBirthdaysPagingSource : PagingSource<Int, Birthday>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Birthday> {
        delay(1000)
        val pageNumber = params.key ?: 0
        val prevKey = if (pageNumber > 0) pageNumber - 1 else null
        val nextKey = if (pageNumber < 11) pageNumber + 1 else null
        return LoadResult.Page(
            data = List(if (nextKey != null) 15 else 5) {
                birthday(month = pageNumber + 1)
            }
                .sortedBy { it.date.dayOfMonth },
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

    private fun birthday(month: Int): Birthday {
        return Birthday(
            id = Random.nextInt().toString(),
            date = LocalDate(2024, month, Random.nextInt(1, 28)),
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
}