package com.nikitakrapo.birthdays.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.components.buttons.IconButton
import com.nikitakrapo.birthdays.components.screens.ErrorScreen
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import com.nikitakrapo.birthdays.utils.Crossfade
import strings.R

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    component: ProfileComponent,
) {
    val state by component.state.collectAsState()
    val showLogoutDialog by component.showLogoutDialog.collectAsState()

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = component::onLogoutCancelled,
            confirmButton = {
                TextButton(onClick = component::onLogoutConfirmed) {
                    Text(text = stringResource(R.string.logout))
                }
            },
            dismissButton = {
                TextButton(onClick = component::onLogoutCancelled) {
                    Text(text = stringResource(R.string.cancel))
                }
            },
            text = {
                Text(text = stringResource(R.string.profile_logout_confirmation))
            }
        )
    }

    Crossfade(
        modifier = modifier,
        targetState = state,
        contentKey = { it::class },
    ) { profileScreenState ->
        when (profileScreenState) {
            ProfileScreenState.Loading -> {
                ProfileScreenLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 64.dp),
                )
            }

            is ProfileScreenState.Loaded -> {
                ProfileScreenLoaded(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(),
                    state = profileScreenState,
                    component = component,
                )
            }

            ProfileScreenState.Error -> {
                ErrorScreen(
                    modifier = modifier
                        .fillMaxSize(),
                    onRetryClick = component::onRetryClicked,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreenLoaded(
    modifier: Modifier = Modifier,
    state: ProfileScreenState.Loaded,
    component: ProfileComponent,
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = {},
            actions = {
                IconButton(
                    imageVector = Icons.Outlined.Edit,
                    onClick = component::onEditProfileClicked,
                    contentDescription = stringResource(R.string.cd_edit_profile),
                )
            }
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = state.profileInfo.username,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = BirthdaysTheme.typography.headlineSmall,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = state.profileInfo.birthday.getProfileBirthdayText(),
            style = BirthdaysTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.weight(1f))
        ListItem(
            modifier = Modifier
                .clickable(onClick = component::onLogoutClicked),
            headlineContent = {
                Text(text = "Logout")
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Logout,
                    tint = BirthdaysTheme.colorScheme.primary,
                    contentDescription = null,
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Color.Transparent,
                headlineColor = BirthdaysTheme.colorScheme.primary,
            )
        )
    }
}

@Composable
private fun ProfileScreenLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun ProfileScreen_Preview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            ProfileScreen(
                component = ProfileComponentPreview,
            )
        }
    }
}