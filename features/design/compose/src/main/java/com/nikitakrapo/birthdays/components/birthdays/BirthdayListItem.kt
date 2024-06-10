package com.nikitakrapo.birthdays.components.birthdays

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nikitakrapo.birthdays.model.Birthday
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import kotlinx.datetime.LocalDate

@Composable
fun BirthdayListItem(
    birthday: Birthday,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .widthIn(max = 460.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        birthday.imageUrl?.let {
            if (LocalInspectionMode.current) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(BirthdaysTheme.shapes.medium)
                        .background(BirthdaysTheme.colorScheme.secondaryContainer),
                )
            } else {
                AsyncImage(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(BirthdaysTheme.shapes.medium),
                    model = it,
                    placeholder = ColorPainter(BirthdaysTheme.colorScheme.outline),
                    error = ColorPainter(BirthdaysTheme.colorScheme.outline),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = birthday.title,
                style = BirthdaysTheme.typography.titleMedium,
                color = BirthdaysTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = birthday.date.toString(),
                style = BirthdaysTheme.typography.bodyMedium,
                color = BirthdaysTheme.colorScheme.onSurfaceVariant,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.NavigateNext,
            contentDescription = null,
        )
    }
}

@Preview
@Composable
private fun BirthdayListItemPreview() {
    BirthdaysTheme {
        Surface {
            BirthdayListItem(
                birthday = Birthday(
                    id = "123",
                    title = "Michael Jackson's birthday",
                    date = LocalDate(2024, 5, 5),
                    imageUrl = "",
                ),
                onClick = {},
            )
        }
    }
}