package com.nikitakrapo.birthdays.feed.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.theme.BirthdaysTheme

@Composable
fun TripListItem(
    modifier: Modifier = Modifier,
    component: TripItemComponent,
) {
    val state by component.state.collectAsState()
    Column(
        modifier = modifier
            .clip(BirthdaysTheme.shapes.medium)
            .background(BirthdaysTheme.colorScheme.surfaceVariant)
            .clickable(onClick = component::onTripClick)
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth(),
        ) {
            Text(
                text = state.title,
                style = BirthdaysTheme.typography.titleMedium,
                color = BirthdaysTheme.colorScheme.onSurface,
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = state.date,
            style = BirthdaysTheme.typography.bodyMedium,
            color = BirthdaysTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Preview
@Composable
private fun TripListItem_Preview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            TripListItem(
                modifier = Modifier
                    .size(
                        width = 300.dp,
                        height = 200.dp,
                    ),
                component = TripItemComponentPreview,
            )
        }
    }
}