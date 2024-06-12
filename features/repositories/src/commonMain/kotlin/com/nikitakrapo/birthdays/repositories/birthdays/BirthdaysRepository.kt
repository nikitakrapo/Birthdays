package com.nikitakrapo.birthdays.repositories.birthdays

import androidx.paging.PagingData
import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.coroutines.flow.Flow

interface BirthdaysRepository {

    fun getBirthdaysPaging(): Flow<PagingData<Birthday>>
}