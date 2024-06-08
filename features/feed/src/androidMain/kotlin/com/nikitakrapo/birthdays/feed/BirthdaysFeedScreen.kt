package com.nikitakrapo.birthdays.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.components.shimmer.ShimmerBox
import com.nikitakrapo.birthdays.theme.BirthdaysTheme

@Composable
fun BirthdaysFeedScreen(
    modifier: Modifier = Modifier,
    component: BirthdaysFeedComponent,
) {
    val state by component.state.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(top = 16.dp),
    ) {
        when (state) {
            BirthdaysFeedScreenState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        BirthdaysCalendarShimmer()
                    }
                }
            }
        }
    }
}

@Composable
private fun BirthdaysCalendarShimmer(
    modifier: Modifier = Modifier,
) {
    ShimmerBox(
        modifier = modifier
            .size(
                width = 336.dp,
                height = 384.dp,
            )
            .clip(RoundedCornerShape(16.dp))
            .background(BirthdaysTheme.colorScheme.primaryContainer),
    )
}
