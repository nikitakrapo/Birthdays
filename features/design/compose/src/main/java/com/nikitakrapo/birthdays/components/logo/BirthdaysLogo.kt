package com.nikitakrapo.birthdays.components.logo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.theme.BirthdaysTheme

@Composable
fun BirthdaysLogo(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(128.dp)
            .clip(CircleShape)
            .background(BirthdaysTheme.colorScheme.tertiary),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "TODO LOGO",
            fontWeight = FontWeight.W500,
            color = BirthdaysTheme.colorScheme.onTertiary,
        )
    }
}

@Preview
@Composable
private fun BirthdaysLogoPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            BirthdaysLogo()
        }
    }
}