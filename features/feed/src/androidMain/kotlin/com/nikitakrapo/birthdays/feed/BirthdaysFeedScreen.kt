package com.nikitakrapo.birthdays.feed

import androidx.compose.animation.core.SnapSpec
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.nikitakrapo.birthdays.components.birthdays.BirthdayListItem
import com.nikitakrapo.birthdays.components.birthdays.BirthdayListItemShimmer
import com.nikitakrapo.birthdays.components.shimmer.ShimmerTextLine
import com.nikitakrapo.birthdays.theme.BirthdaysTheme

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
    ) {
        if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
            item {
                BirthdayFeedHeaderShimmer(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(
                            vertical = 8.dp,
                            horizontal = 16.dp,
                        ),
                )
            }

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
            key = { lazyPagingItems[it]!!.key() }
        ) { index ->
            val item = lazyPagingItems[index]!!
            when (item) {
                is BirthdayFeedListItem.BirthdayItem -> {
                    BirthdayListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItem(placementSpec = SnapSpec()),
                        birthday = item.birthday,
                        onClick = { component.onBirthdayClicked(item.birthday) },
                    )
                }
                is BirthdayFeedListItem.HeaderItemFeed -> {
                    BirthdayFeedHeader(
                        modifier = Modifier
                            .padding(
                                vertical = 8.dp,
                                horizontal = 16.dp,
                            ),
                        text = item.text,
                    )
                }
            }
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

@Composable
private fun BirthdayFeedHeader(
    text: String,
    modifier: Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = BirthdaysTheme.typography.titleMedium,
    )
}

@Composable
private fun BirthdayFeedHeaderShimmer(
    modifier: Modifier,
) {
    ShimmerTextLine(
        modifier = modifier,
        textStyle = BirthdaysTheme.typography.titleMedium,
    )
}

private fun BirthdayFeedListItem.key() = when (this) {
    is BirthdayFeedListItem.BirthdayItem -> birthday.id
    is BirthdayFeedListItem.HeaderItemFeed -> text
}
