package com.nikitakrapo.birthdays.feed

import androidx.paging.PagingData
import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

interface BirthdaysFeedComponent {

    val state: StateFlow<BirthdaysFeedScreenState>
    val birthdaysPagingDataState: Flow<PagingData<BirthdayListItem>>

    fun onDateSelected(date: LocalDate)
    fun onBirthdayClicked(birthday: Birthday)
}