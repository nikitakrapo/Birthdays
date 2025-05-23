package com.nikitakrapo.birthdays.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.NavigateNext
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.chooser.BirthdayChooserDialog
import com.nikitakrapo.birthdays.components.buttons.BackButton
import com.nikitakrapo.birthdays.components.buttons.IconButton
import com.nikitakrapo.birthdays.components.text.TransparentTextFieldDefaults
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import strings.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(
    component: ProfileEditComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.state.collectAsState()
    val dialogSlot by component.dialogSlot.collectAsState()

    when (val instance = dialogSlot.child?.instance) {
        is ProfileEditComponent.ProfileEditDialog.BirthdayChooser -> BirthdayChooserDialog(
            component = instance.component,
        )
        null -> {}
    }

    Column(
        modifier = modifier,
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.profile_info))
            },
            navigationIcon = {
                BackButton(onClick = component::onBackClicked)
            },
            actions = {
                AnimatedVisibility(visible = state.isConfirmAllowed && !state.isLoading) {
                    IconButton(
                        imageVector = Icons.Outlined.Done,
                        onClick = component::onConfirmClicked,
                        contentDescription = stringResource(R.string.cd_edit_profile_confirm),
                    )
                }
                AnimatedVisibility(visible = state.isLoading) {
                    Box(modifier = Modifier.size(48.dp)) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.Center),
                            strokeWidth = 2.dp,
                        )
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 460.dp),
            value = state.username,
            onValueChange = component::onUsernameChanged,
            label = {
                Text(text = stringResource(R.string.login_username_label))
            },
            colors = TransparentTextFieldDefaults.colors(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            singleLine = true,
            maxLines = 1,
        )

        Spacer(modifier = Modifier.height(8.dp))

        ListItem(
            modifier = Modifier
                .clickable(onClick = component::onChangeBirthdayClicked),
            headlineContent = {
                Text(text = stringResource(R.string.birthday))
            },
            supportingContent = {
                Text(text = state.birthday.getProfileBirthdayText())
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.NavigateNext,
                    contentDescription = stringResource(R.string.cd_edit_birthday),
                )
            }
        )
    }
}

@Preview
@Composable
private fun ProfileEditScreenPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            ProfileEditScreen(
                component = ProfileEditComponentPreview,
            )
        }
    }
}
