package com.nikitakrapo.trips.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.trips.design.theme.TripsTheme
import strings.R

@Composable
fun TripsFeedScreen(
    modifier: Modifier = Modifier,
    component: TripsFeedComponent,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
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
    TripsTheme {
        Surface(color = TripsTheme.colorScheme.background) {
            TripsFeedScreen(
                modifier = Modifier
                    .fillMaxSize(),
                component = TripsFeedComponentPreview
            )
        }
    }
}
