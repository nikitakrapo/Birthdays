package com.nikitakrapo.birthdays.repositories.birthdays

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nikitakrapo.birthdays.model.Birthday
import com.nikitakrapo.birthdays.network.result.NetworkResult
import com.nikitakrapo.birthdays.network.result.map
import com.nikitakrapo.birthdays.remote.BirthdaysApi
import com.nikitakrapo.birthdays.remote.data.AddBirthdayDto
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format

internal class BirthdaysRepositoryImpl(
    private val api: BirthdaysApi,
) : BirthdaysRepository {

    override suspend fun addBirthday(name: String, date: LocalDate): NetworkResult<Unit> {
        return api.addLocalBirthday(
            request = AddBirthdayDto(
                displayDate = date.format(LocalDate.Formats.ISO),
                displayName = name,
            )
        ).map { }
    }

    override fun getBirthdaysPaging(): Flow<PagingData<Birthday>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { BirthdaysPagingSource(birthdaysApi = api) },
        ).flow
    }
}
