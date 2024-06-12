package com.nikitakrapo.birthdays.feed

import androidx.paging.PagingData
import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDate

object BirthdaysFeedComponentPreview : BirthdaysFeedComponent {

    override val state: StateFlow<BirthdaysFeedScreenState> = MutableStateFlow(
        BirthdaysFeedScreenState.Loading
    )
    override val birthdaysPagingDataState: Flow<PagingData<Birthday>> = flow {
        PagingData.from(listOf(
            Birthday.forPreview(),
            Birthday.forPreview(),
            Birthday.forPreview(),
            Birthday.forPreview(),
            Birthday.forPreview(),
        ))
    }

    override fun onDateSelected(date: LocalDate) = Unit
    override fun onBirthdayClicked(birthday: Birthday) = Unit
}