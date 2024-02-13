package com.nikitakrapo.trips

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nikitakrapo.trips.design.theme.TripsTheme
import com.nikitakrapo.trips.registration.RegistrationComponent
import com.nikitakrapo.trips.registration.RegistrationComponentPreview
import strings.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegistrationScreen(
    modifier: Modifier = Modifier,
    component: RegistrationComponent,
) {
    val state by component.state.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Horizontal,
            )
            .imePadding(),
    ) {
        TopAppBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(1f),
            title = {},
            navigationIcon = {
                IconButton(onClick = component::onBackClicked) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = stringResource(id = R.string.cd_back)
                    )
                }
            }
        )
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
                text = stringResource(R.string.register_title),
                style = TripsTheme.typography.headlineSmall,
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
            FilledTonalButton(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .align(Alignment.End),
                onClick = component::onRegisterClicked,
                enabled = !state.isLoading,
            ) {
                Text(text = stringResource(R.string.register_register))
            }
        }
    }
}

@Preview
@Composable
private fun RegistrationScreen_Preview() {
    TripsTheme {
        RegistrationScreen(
            component = RegistrationComponentPreview,
        )
    }
}