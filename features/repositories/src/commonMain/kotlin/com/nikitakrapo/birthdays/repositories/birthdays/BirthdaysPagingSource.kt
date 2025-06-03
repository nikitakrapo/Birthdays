package com.nikitakrapo.birthdays.repositories.birthdays

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nikitakrapo.birthdays.model.Birthday
import com.nikitakrapo.birthdays.network.result.fold
import com.nikitakrapo.birthdays.remote.BirthdaysApi

internal class BirthdaysPagingSource(
    private val birthdaysApi: BirthdaysApi,
) : PagingSource<Int, Birthday>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Birthday> {
        val pageNumber = params.key ?: 0
        val offset = pageNumber * params.loadSize

        val response = birthdaysApi.getBirthdays(
            offset = offset,
            count = params.loadSize
        )

        val data = response.fold(
            onSuccess = { it },
            onError = { return LoadResult.Error(it) }
        )

        val prevKey = if (pageNumber > 0) pageNumber - 1 else null

        val nextKey = if (data.isNotEmpty()) pageNumber + 1 else null

        return LoadResult.Page(
            data = data,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Birthday>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}