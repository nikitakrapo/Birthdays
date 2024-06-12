package com.nikitakrapo.birthdays.feed

import androidx.compose.animation.core.SnapSpec
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.nikitakrapo.birthdays.components.birthdays.BirthdayListItem
import com.nikitakrapo.birthdays.components.birthdays.BirthdayListItemShimmer

@Composable
fun BirthdaysFeedScreen(
    modifier: Modifier = Modifier,
    component: BirthdaysFeedComponent,
) {
    val state by component.state.collectAsState()
    val lazyPagingItems = component.birthdaysPagingDataState.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
            items(
                count = 3,
                key = { "refresh-$it" }
            ) {
                BirthdayListItemShimmer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                )
            }
        }

        items(
            count = lazyPagingItems.itemCount,
            key = { lazyPagingItems[it]!!.id }
        ) { index ->
            val item = lazyPagingItems[index]!!
            BirthdayListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem(placementSpec = SnapSpec()),
                birthday = item,
                onClick = { component.onBirthdayClicked(item) },
            )
        }

        if (lazyPagingItems.loadState.append == LoadState.Loading) {
            items(
                count = 3,
                key = { "append-$it" }
            ) {
                BirthdayListItemShimmer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(placementSpec = SnapSpec()),
                )
            }
        }
    }
    return
}
