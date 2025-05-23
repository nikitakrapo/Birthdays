package com.nikitakrapo.birthdays.feed

import androidx.paging.PagingData
import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface BirthdaysFeedComponent {

    val state: StateFlow<BirthdaysFeedScreenState>
    val birthdaysPagingDataState: Flow<PagingData<BirthdayFeedListItem>>

    fun onAddClicked()
    fun onBirthdayClicked(birthday: Birthday)
}