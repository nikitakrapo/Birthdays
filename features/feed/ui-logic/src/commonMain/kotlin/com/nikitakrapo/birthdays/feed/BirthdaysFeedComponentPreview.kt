package com.nikitakrapo.birthdays.feed

import androidx.paging.PagingData
import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

object BirthdaysFeedComponentPreview : BirthdaysFeedComponent {

    override val state: StateFlow<BirthdaysFeedScreenState> = MutableStateFlow(
        BirthdaysFeedScreenState.Loading
    )
    override val birthdaysPagingDataState: Flow<PagingData<BirthdayFeedListItem>> = flow {
        PagingData.from(listOf(
            BirthdayFeedListItem.BirthdayItem(Birthday.forPreview()),
            BirthdayFeedListItem.BirthdayItem(Birthday.forPreview()),
            BirthdayFeedListItem.BirthdayItem(Birthday.forPreview()),
            BirthdayFeedListItem.BirthdayItem(Birthday.forPreview()),
            BirthdayFeedListItem.BirthdayItem(Birthday.forPreview()),
        ))
    }

    override fun onAddClicked() = Unit
    override fun onBirthdayClicked(birthday: Birthday) = Unit
}