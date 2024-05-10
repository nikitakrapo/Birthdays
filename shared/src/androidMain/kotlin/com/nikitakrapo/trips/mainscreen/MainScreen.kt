package com.nikitakrapo.trips.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.AirplaneTicket
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import com.nikitakrapo.birthdays.wizard.BirthdayChooser
import com.nikitakrapo.birthdays.wizard.chooser.DateChooserComponentPreview
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
                is MainComponent.MainChild.BirthdaysFeed -> BirthdaysTheme {
                    Box(
                        modifier = Modifier
                            .statusBarsPadding(),
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(16.dp),
                        ) {
                            BirthdayChooser(
                                modifier = Modifier
                                    .padding(8.dp),
                                component = DateChooserComponentPreview,
                            )
                        }
                    }
                }
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
                    title = context.getString(R.string.birthdays),
                    icon = Icons.Outlined.CalendarMonth,
                ),
                BottomBarItem(
                    title = context.getString(R.string.trips),
                    icon = Icons.AutoMirrored.Outlined.AirplaneTicket,
                ),
                BottomBarItem(
                    title = context.getString(R.string.profile),
                    icon = Icons.Outlined.Person,
                )
            ),
            selectedItem = when (child.active.instance) {
                is MainComponent.MainChild.BirthdaysFeed -> 0
                is MainComponent.MainChild.TripsFeed -> 1
                is MainComponent.MainChild.Profile -> 2
            },
            onItemClick = {
                when (it) {
                    0 -> mainComponent.onFeedClicked()
                    1 -> mainComponent.onTripsClicked()
                    2 -> mainComponent.onProfileClicked()
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
        Surface(color = TripsTheme.colorScheme.background) {
            MainScreen(mainComponent = MainComponentPreview)
        }
    }
}