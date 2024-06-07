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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.login.LoginComponent
import com.nikitakrapo.birthdays.login.LoginComponentPreview
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import strings.R

@Composable
internal fun LoginScreen(
    modifier: Modifier = Modifier,
    component: LoginComponent,
) {
    val state by component.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Horizontal,
            )
            .imePadding(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.login_title),
            style = BirthdaysTheme.typography.headlineSmall,
        )
        Spacer(modifier = Modifier.height(16.dp))
        EmailPasswordFields(
            email = state.email,
            onEmailChanged = component::onEmailTextChanged,
            password = state.password,
            onPasswordChanged = component::onPasswordTextChanged,
            isEnabled = !state.isLoading,
            error = state.error,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            TextButton(
                onClick = component::onRegisterClicked,
                enabled = !state.isLoading,
            ) {
                Text(text = stringResource(R.string.login_create_new_account))
            }
            FilledTonalButton(
                onClick = component::onDoneClicked,
                enabled = !state.isLoading && state.error == null,
            ) {
                Text(text = stringResource(R.string.login_login))
            }
        }
    }
}

@Preview
@Composable
private fun AuthorizationLandingScreen_Preview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            LoginScreen(
                component = LoginComponentPreview,
            )
        }
    }
}