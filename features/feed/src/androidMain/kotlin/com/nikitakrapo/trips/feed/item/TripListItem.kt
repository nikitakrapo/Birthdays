package com.nikitakrapo.trips.feed.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.trips.design.theme.TripsTheme
import strings.R

@Composable
fun TripListItem(
    modifier: Modifier = Modifier,
    component: TripItemComponent,
) {
    val state by component.state.collectAsState()
    Column(
        modifier = modifier
            .clip(TripsTheme.shapes.medium)
            .background(TripsTheme.colorScheme.surfaceVariant)
            .clickable(onClick = component::onTripClick),
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth(),
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = state.title,
                style = TripsTheme.typography.titleMedium,
                color = TripsTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                modifier = Modifier,
                onClick = component::onOverflowClick,
            ) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = stringResource(R.string.cd_overflow_menu),
                )
            }
        }
    }
}

@Preview
@Composable
private fun TripListItem_Preview() {
    TripsTheme {
        Surface(color = TripsTheme.colorScheme.background) {
            TripListItem(
                component = TripItemComponentPreview,
            )
        }
    }
}