package com.nikitakrapo.trips.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AirplaneTicket
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.nikitakrapo.trips.design.components.BottomBarItem
import com.nikitakrapo.trips.design.components.BottomNavigationBar
import com.nikitakrapo.trips.design.theme.TripsTheme
import com.nikitakrapo.trips.feed.TripsFeedScreen
import com.nikitakrapo.trips.profile.ProfileScreen
import strings.R

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainComponent: MainComponent,
) {
    Column(
        modifier = modifier,
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
                is MainComponent.MainChild.Profile -> ProfileScreen(
                    modifier = Modifier
                        .statusBarsPadding(),
                    component = instance.component,
                )
            }
        }
        val context = LocalContext.current
        BottomNavigationBar(
            items = listOf(
                BottomBarItem(
                    title = context.getString(R.string.trips),
                    icon = Icons.Outlined.AirplaneTicket,
                ),
                BottomBarItem(
                    title = context.getString(R.string.profile),
                    icon = Icons.Outlined.Person,
                )
            ),
            selectedItem = when (child.active.instance) {
                is MainComponent.MainChild.TripsFeed -> 0
                is MainComponent.MainChild.Profile -> 1
            },
            onItemClick = {
                when (it) {
                    0 -> mainComponent.onTripsClicked()
                    1 -> mainComponent.onProfileClicked()
                    else -> error("Unhandled bottom bar click with $it index")
                }
            },
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