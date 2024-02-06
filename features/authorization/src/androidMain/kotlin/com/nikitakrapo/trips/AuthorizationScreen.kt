package com.nikitakrapo.trips

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.ButtonDefaults.ButtonWithIconContentPadding
import androidx.compose.material3.ButtonDefaults.IconSpacing
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.trips.design.theme.TripsTheme

@Composable
fun AuthorizationScreen(
    modifier: Modifier = Modifier,
    component: AuthorizationComponent,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        Text(
            text = "Sign up to plan trips or smth",
            style = TripsTheme.typography.headlineSmall,
        )
        FilledTonalButton(
            onClick = component::onEmailLoginClicked,
            contentPadding = ButtonWithIconContentPadding,
        ) {
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(IconSpacing))
            Text(text = "Sign up with email")
        }
        Text(
            text = "Already have an account? Sign in",
            style = TripsTheme.typography.bodyMedium,
        )
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
private fun AuthorizationScreen_Preview() {
    TripsTheme {
        Surface(color = TripsTheme.colorScheme.background) {
            AuthorizationScreen(
                component = AuthorizationComponentPreview,
            )
        }
    }
}