package com.nikitakrapo.birthdays

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.design.components.PasswordTextField
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import strings.R

@Composable
internal fun EmailPasswordFields(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    isEnabled: Boolean,
    error: String?,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 460.dp)
                .padding(horizontal = 24.dp),
            value = email,
            onValueChange = onEmailChanged,
            label = {
                Text(text = stringResource(R.string.login_email_label))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
            isError = error != null,
            enabled = isEnabled,
            singleLine = true,
            maxLines = 1,
        )
        Spacer(modifier = Modifier.height(4.dp))
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 460.dp)
                .padding(horizontal = 24.dp),
            value = password,
            onValueChange = onPasswordChanged,
            enabled = isEnabled,
            isError = error != null,
        )
        AnimatedVisibility(visible = error != null) {
            Text(
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 16.dp,
                    ),
                text = error ?: "",
                style = BirthdaysTheme.typography.titleSmall,
                color = BirthdaysTheme.colorScheme.error,
            )
        }
    }
}