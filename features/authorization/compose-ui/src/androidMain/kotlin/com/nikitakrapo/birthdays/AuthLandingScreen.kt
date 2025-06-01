package com.nikitakrapo.birthdays

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.landing.AuthLandingComponent
import com.nikitakrapo.birthdays.landing.AuthLandingComponentPreview
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import strings.R

@Composable
fun AuthLandingScreen(
    component: AuthLandingComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Horizontal,
            )
            .imePadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.auth_landing_title),
            style = BirthdaysTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(horizontal = 8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.weight(1f))

            FilledTonalButton(
                onClick = component::onLoginClicked,
            ) {
                Text(text = stringResource(R.string.login_login))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        state.error?.let {
            Text(it)
        }
    }
}

@Preview
@Composable
private fun AuthLandingScreenPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            AuthLandingScreen(
                component = AuthLandingComponentPreview,
            )
        }
    }
}