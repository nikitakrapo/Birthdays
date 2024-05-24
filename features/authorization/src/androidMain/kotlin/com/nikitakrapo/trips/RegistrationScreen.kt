package com.nikitakrapo.trips

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowRight
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.nikitakrapo.birthdays.chooser.BirthdayChooserDialog
import com.nikitakrapo.trips.design.components.PasswordTextField
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
    val dialogSlot by component.dialogSlot.collectAsState()

    when (val instance = dialogSlot.child?.instance) {
        is RegistrationComponent.RegistrationDialog.BirthdayChooser -> BirthdayChooserDialog(
            component = instance.component,
        )
        null -> {}
    }

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
            RegistrationTextField(
                value = state.username,
                onValueChange = component::onUsernameTextChanged,
                label = stringResource(R.string.login_username_label),
                isEnabled = !state.isLoading,
                error = state.error,
            )
            Spacer(modifier = Modifier.height(4.dp))
            RegistrationTextField(
                value = state.email,
                onValueChange = component::onEmailTextChanged,
                label = stringResource(R.string.login_email_label),
                isEnabled = !state.isLoading,
                error = state.error,
            )
            Spacer(modifier = Modifier.height(4.dp))
            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 460.dp)
                    .padding(horizontal = 24.dp),
                value = state.password,
                onValueChange = component::onPasswordTextChanged,
                enabled = !state.isLoading,
                isError = state.error != null,
            )
            Spacer(modifier = Modifier.height(4.dp))
            val density = LocalDensity.current
            Spacer(modifier = Modifier.height(with (density) { 8.sp.toDp() }))
            Button(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .height(56.dp)
                    .fillMaxWidth(),
                onClick = component::onSelectBirthdayClicked,
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 16.dp,
                ),
                shape = RoundedCornerShape(4.dp),
            ) {
                Text(
                    text = if (state.isBirthdaySelected) {
                        stringResource(R.string.edit_birthday)
                    } else {
                        stringResource(R.string.select_birthday)
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = if (state.isBirthdaySelected) {
                        Icons.Outlined.Check
                    } else {
                        Icons.AutoMirrored.Outlined.ArrowRight
                    },
                    contentDescription = null,
                )
            }
            AnimatedVisibility(visible = state.error != null) {
                Text(
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 16.dp,
                        ),
                    text = state.error ?: "",
                    style = TripsTheme.typography.titleSmall,
                    color = TripsTheme.colorScheme.error,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            FilledTonalButton(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .align(Alignment.End),
                onClick = component::onRegisterClicked,
                enabled = state.isRegisterButtonActive,
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