package com.nikitakrapo.birthdays.feed

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.birthdays.di.Di
import com.nikitakrapo.birthdays.model.Birthday
import com.nikitakrapo.birthdays.repositories.birthdays.BirthdaysRepository
import com.nikitakrapo.birthdays.utils.decompose.coroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class BirthdaysFeedComponentImpl(
    componentContext: ComponentContext,
    refreshTrigger: BirthdaysFeedRefreshTrigger,
    private val openAddBirthday: () -> Unit,
) : BirthdaysFeedComponent, ComponentContext by componentContext {

    private val coroutineScope = coroutineScope()

    private val birthdaysRepository by Di.inject<BirthdaysRepository>()

    private val stateFlow: MutableStateFlow<BirthdaysFeedScreenState> =
        MutableStateFlow(BirthdaysFeedScreenState.Loading)
    override val state = stateFlow.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val birthdaysPagingDataState: Flow<PagingData<BirthdayFeedListItem>> = refreshTrigger.triggers
        .onStart { emit(Unit) }
        .flatMapConcat {
            birthdaysRepository.getBirthdaysPaging()
                .cachedIn(coroutineScope)
        }
        .map { pagingData -> pagingData.map { BirthdayFeedListItem.BirthdayItem(it) } }
        .map { pagingData ->
            pagingData.insertSeparators { prevItem, currItem ->
                val prevMonth = prevItem?.birthday?.date?.month
                val currMonth = currItem?.birthday?.date?.month
                when {
                    prevMonth == null && currMonth != null -> BirthdayFeedListItem.HeaderItemFeed(currMonth.name)
                    prevMonth != null && currMonth != null && prevMonth != currMonth -> BirthdayFeedListItem.HeaderItemFeed(currMonth.name)
                    else -> null
                }
            }
        }

    init {
        fetchBirthdays()
    }

    override fun onAddClicked() {
        openAddBirthday()
    }

    override fun onBirthdayClicked(birthday: Birthday) {
        TODO("Not yet implemented")
    }

    private fun fetchBirthdays() {
        coroutineScope.launch {
            val birthdaysResult = Result.success<List<Birthday>>(emptyList())
            birthdaysResult.fold(
                onSuccess = {
                    stateFlow.value = BirthdaysFeedScreenState.Loaded(
                        initialDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
                        startDate = LocalDate(2024, 1, 1),
                        endDate = LocalDate(2026, 1, 1),
                        birthdays = it,
                    )
                },
                onFailure = { TODO() },
            )
        }
    }
}