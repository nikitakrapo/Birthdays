package com.nikitakrapo.birthdays.feed

import androidx.compose.animation.core.SnapSpec
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.nikitakrapo.birthdays.components.birthdays.BirthdayListItem
import com.nikitakrapo.birthdays.components.birthdays.BirthdayListItemShimmer
import com.nikitakrapo.birthdays.components.shimmer.ShimmerTextLine
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer

@Composable
fun BirthdaysFeedScreen(
    component: BirthdaysFeedComponent,
    modifier: Modifier = Modifier,
) {
    val lazyPagingItems = component.birthdaysPagingDataState.collectAsLazyPagingItems()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        val shimmer = rememberShimmer(ShimmerBounds.Window)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(top = 16.dp),
        ) {
            if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
                item {
                    BirthdayFeedHeaderShimmer(
                        shimmer = shimmer,
                        modifier = Modifier
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
                        shimmer = shimmer,
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
                        shimmer = shimmer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItem(placementSpec = SnapSpec()),
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = component::onAddClicked,
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(strings.R.string.cd_add_birthday)
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
        )
    }
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
    shimmer: Shimmer,
) {
    Box(modifier) {
        ShimmerTextLine(
            shimmer = shimmer,
            textStyle = BirthdaysTheme.typography.titleMedium,
            isAllCaps = true,
            modifier = Modifier.width(100.dp),
        )
    }
}

private fun BirthdayFeedListItem.key() = when (this) {
    is BirthdayFeedListItem.BirthdayItem -> birthday.id
    is BirthdayFeedListItem.HeaderItemFeed -> text
}

@Preview
@Composable
private fun BirthdaysFeedScreenPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            BirthdaysFeedScreen(
                component = BirthdaysFeedComponentPreview,
            )
        }
    }
}