package com.nikitakrapo.trips.root

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.nikitakrapo.trips.AuthorizationScreen
import com.nikitakrapo.trips.design.theme.TripsTheme
import com.nikitakrapo.trips.mainscreen.MainScreen

@Composable
fun RootScreen(
    modifier: Modifier = Modifier,
    rootComponent: RootComponent,
) {
    val stack by rootComponent.stack.collectAsState()

    Children(
        modifier = modifier,
        stack = stack
    ) { child ->
        val instance = child.instance
        when (instance) {
            is RootComponent.RootChild.Main -> MainScreen(mainComponent = instance.component)
            is RootComponent.RootChild.Authorization -> AuthorizationScreen(component = instance.component)
        }
    }
}

@Preview
@Composable
private fun RootScreen_Preview() {
    TripsTheme {
        Surface(color = TripsTheme.colorScheme.background) {
            RootScreen(
                rootComponent = RootComponentPreview,
            )
        }
    }
}