package com.nikitakrapo.trips.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.nikitakrapo.trips.bottomnav.BottomNavigationBar
import com.nikitakrapo.trips.design.theme.TripsTheme
import com.nikitakrapo.trips.feed.TripsFeedScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainComponent: MainComponent,
) {
    Column(
        modifier = modifier
            .statusBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        val child by mainComponent.stack.collectAsState()
        Children(
            modifier = Modifier.weight(1f),
            stack = child,
            animation = mainScreenChildrenAnimation(),
        ) {
            when (val instance = it.instance) {
                is MainComponent.MainChild.TripsFeed -> TripsFeedScreen(
                    component = instance.component,
                )
                MainComponent.MainChild.Profile -> Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Profile",
                )
            }
        }
        BottomNavigationBar(
            component = mainComponent.bottomNavigationComponent,
        )
    }
}

@Preview
@Composable
private fun MainScreen_Preview() {
    TripsTheme {
        MainScreen(mainComponent = MainComponentPreview)
    }
}