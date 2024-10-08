package com.nikitakrapo.birthdays.root

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.nikitakrapo.birthdays.AuthorizationScreen
import com.nikitakrapo.birthdays.mainscreen.MainScreen
import com.nikitakrapo.birthdays.theme.BirthdaysTheme

@Composable
fun RootScreen(
    modifier: Modifier = Modifier,
    rootComponent: RootComponent,
) {
    val stack by rootComponent.stack.collectAsState()

    Children(
        modifier = modifier,
        stack = stack,
        animation = stackAnimation(),
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
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            RootScreen(
                rootComponent = RootComponentPreview,
            )
        }
    }
}