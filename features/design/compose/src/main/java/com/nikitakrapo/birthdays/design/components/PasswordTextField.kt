package com.nikitakrapo.birthdays.design.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import strings.R

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean,
    isError: Boolean,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        label = {
            Text(
                text = stringResource(R.string.login_password_label)
            )
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Crossfade(targetState = passwordVisible) {
                    if (it) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            }
        },
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        isError = isError,
        singleLine = true,
        maxLines = 1,
    )
}

@Preview
@Composable
private fun PasswordTextField_Preview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            PasswordTextField(
                value = "password",
                onValueChange = {},
                enabled = true,
                isError = false,
            )
        }
    }
}