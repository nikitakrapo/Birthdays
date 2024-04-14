package com.nikitakrapo.trips.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.trips.design.theme.TripsTheme
import strings.R

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    component: ProfileComponent,
) {
    val state by component.state.collectAsState()

    if (state.showLogoutDialog) {
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp),
    ) {
        // Will be header block
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = state.email,
                style = TripsTheme.typography.headlineSmall,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        val profileListItemColors = ListItemDefaults.colors(
            containerColor = Color.Transparent,
            headlineColor = TripsTheme.colorScheme.onBackground,
        )
        ListItem(
            modifier = Modifier
                .clickable(onClick = component::onSettingsClick),
            headlineContent = {
                Text(text = "Settings")
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = null,
                )
            },
            colors = profileListItemColors
        )
        HorizontalDivider()
        ListItem(
            modifier = Modifier
                .clickable(onClick = component::onLogoutClick),
            headlineContent = {
                Text(text = "Logout")
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Outlined.Logout,
                    tint = TripsTheme.colorScheme.primary,
                    contentDescription = null,
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Color.Transparent,
                headlineColor = TripsTheme.colorScheme.primary,
            )
        )
    }
}

@Preview
@Composable
private fun ProfileScreen_Preview() {
    TripsTheme {
        Surface(color = TripsTheme.colorScheme.background) {
            ProfileScreen(
                component = ProfileComponentPreview,
            )
        }
    }
}