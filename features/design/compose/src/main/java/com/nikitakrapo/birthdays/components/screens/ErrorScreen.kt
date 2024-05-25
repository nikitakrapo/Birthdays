package com.nikitakrapo.birthdays.components.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.birthdays.theme.BirthdaysTheme

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onRefreshClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(
            modifier = Modifier,
            onClick = onRefreshClick,
        ) {
            Icon(
                imageVector = Icons.Outlined.Refresh,
                contentDescription = null,
            )
        }
        Text(
            text = stringResource(strings.R.string.network_error),
        )
    }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            ErrorScreen(
                onRefreshClick = {},
            )
        }
    }
}