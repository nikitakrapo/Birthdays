package com.nikitakrapo.birthdays.repositories.birthdays

import androidx.paging.PagingData
import com.nikitakrapo.birthdays.model.Birthday
import com.nikitakrapo.birthdays.network.result.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface BirthdaysRepository {

    suspend fun addBirthday(name: String, date: LocalDate): NetworkResult<Unit>

    fun getBirthdaysPaging(): Flow<PagingData<Birthday>>
}