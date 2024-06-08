package com.nikitakrapo.birthdays.components.calendar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.components.calendar.DateChooserDefaults.DaySize
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import com.nikitakrapo.birthdays.utils.ThemedPreview

@Composable
fun Day(
    modifier: Modifier = Modifier,
    day: Int,
    isChosen: Boolean,
    isActive: Boolean,
    hasEvents: Boolean,
    onClick: () -> Unit,
) {
    val dayContentDescription = stringResource(strings.R.string.cd_day_of_month, day)
    val bgColor by animateColorAsState(
        targetValue = if (isChosen) {
            BirthdaysTheme.colorScheme.primary
        } else {
            Color.Transparent
        }
    )
    Box(
        modifier = modifier
            .size(DaySize)
            .clip(BirthdaysTheme.shapes.small)
            .semantics { contentDescription = dayContentDescription }
            .clickable(onClick = onClick, enabled = isActive)
            .background(color = bgColor),
        contentAlignment = Alignment.Center,
    ) {
        val textColor by animateColorAsState(
            targetValue = when {
                !isActive -> BirthdaysTheme.colorScheme.outline
                isChosen -> BirthdaysTheme.colorScheme.onPrimary
                else -> BirthdaysTheme.colorScheme.onSurface
            }
        )
        Text(
            text = day.toString(),
            style = BirthdaysTheme.typography.titleMedium,
            color = textColor,
        )
        if (hasEvents) {
            Box(
                modifier = Modifier
                    .size(DaySize)
                    .padding(4.dp),
            ) {
                val color by animateColorAsState(
                    targetValue = if (isChosen) {
                        BirthdaysTheme.colorScheme.onPrimary
                    } else {
                        BirthdaysTheme.colorScheme.primary
                    }
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(color),
                )
            }
        }
    }
}

@ThemedPreview
@Composable
private fun DayPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            Box(modifier = Modifier.padding(16.dp)) {
                Day(
                    day = 10,
                    isActive = true,
                    isChosen = false,
                    hasEvents = true,
                    onClick = {},
                )
            }
        }
    }
}

@ThemedPreview
@Composable
private fun DaySelectedPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            Box(modifier = Modifier.padding(16.dp)) {
                Day(
                    day = 10,
                    isActive = true,
                    isChosen = true,
                    hasEvents = true,
                    onClick = {},
                )
            }
        }
    }
}
