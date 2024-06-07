package com.nikitakrapo.birthdays.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.design.utils.plus
import com.nikitakrapo.birthdays.feed.item.TripListItem
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import strings.R

@Composable
fun TripsFeedScreen(
    modifier: Modifier = Modifier,
    component: TripsFeedComponent,
) {
    val state by component.state.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        when (val viewState = state) {
            is TripsFeedScreenState.Loaded -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = WindowInsets.statusBars.asPaddingValues() + PaddingValues(12.dp),
                ) {
                    items(
                        items = viewState.trips,
                        key = { trip -> trip.id }
                    ) { trip ->
                        TripListItem(
                            modifier = Modifier.animateItem(),
                            component = component.createTripItemComponent(trip),
                        )
                    }
                }
            }
            TripsFeedScreenState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                )
            }
        }
        val context = LocalContext.current
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .semantics {
                    contentDescription = context.getString(R.string.cd_add_trip)
                },
            onClick = component::onAddTripClicked,
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = null,
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 700,
)
@Composable
private fun TripsFeedScreen_Preview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            TripsFeedScreen(
                modifier = Modifier
                    .fillMaxSize(),
                component = TripsFeedComponentPreview()
            )
        }
    }
}
