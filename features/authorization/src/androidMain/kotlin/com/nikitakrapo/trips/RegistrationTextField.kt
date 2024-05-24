package com.nikitakrapo.trips

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
internal fun RegistrationTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isEnabled: Boolean,
    error: String?,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp)
            .padding(horizontal = 24.dp),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        ),
        isError = error != null,
        enabled = isEnabled,
        singleLine = true,
        maxLines = 1,
    )

}