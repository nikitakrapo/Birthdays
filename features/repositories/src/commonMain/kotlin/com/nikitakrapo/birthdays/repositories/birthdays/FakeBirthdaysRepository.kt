package com.nikitakrapo.birthdays.repositories.birthdays

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.coroutines.flow.Flow

internal class FakeBirthdaysRepository : BirthdaysRepository {

    override fun getBirthdaysPaging(): Flow<PagingData<Birthday>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { FakeBirthdaysPagingSource() },
        ).flow
    }
}
