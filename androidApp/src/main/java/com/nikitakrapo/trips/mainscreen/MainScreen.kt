package com.nikitakrapo.trips.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainComponent: MainComponent,
) {
    Column(
        modifier = modifier
            .statusBarsPadding(),
    ) {
        val child by mainComponent.stack.collectAsState()
        Children(
            stack = child,
            animation = mainScreenChildrenAnimation(),
        ) {
            val instance = it.instance
            when (instance) {
                MainComponent.MainChild.Trips -> Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Trips",
                )
                MainComponent.MainChild.Profile -> Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Profile",
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar(component = mainComponent.bottomNavigationComponent)
    }
}

@Preview
@Composable
private fun MainScreen_Preview() {
    TripsTheme {
        MainScreen(mainComponent = MainComponentPreview)
    }
}