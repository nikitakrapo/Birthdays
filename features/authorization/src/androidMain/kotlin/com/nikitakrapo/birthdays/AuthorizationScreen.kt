package com.nikitakrapo.birthdays

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.nikitakrapo.birthdays.theme.BirthdaysTheme

@Composable
fun AuthorizationScreen(
    modifier: Modifier = Modifier,
    component: AuthorizationComponent,
) {
    val childStack by component.child.collectAsState()
    Children(
        modifier = modifier
            .systemBarsPadding(),
        stack = childStack,
        animation = stackAnimation(animator = slide()),
    ) {
        when (val instance = it.instance) {
            is AuthorizationComponent.AuthorizationChild.Login -> {
                LoginScreen(component = instance.component)
            }
            is AuthorizationComponent.AuthorizationChild.Registration -> {
                RegistrationScreen(component = instance.component)
            }
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
private fun AuthorizationScreen_Preview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            AuthorizationScreen(
                component = AuthorizationComponentPreview,
            )
        }
    }
}