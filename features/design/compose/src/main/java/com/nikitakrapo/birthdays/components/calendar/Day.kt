package com.nikitakrapo.birthdays.components.calendar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.nikitakrapo.birthdays.components.calendar.data.DayState
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import com.nikitakrapo.birthdays.utils.ThemedPreview
import kotlinx.datetime.LocalDate

@Composable
fun Day(
    modifier: Modifier = Modifier,
    state: DayState,
    onClick: () -> Unit,
) {
    Day(
        modifier = modifier,
        day = state.day.dayOfMonth,
        isChosen = state.isChosen,
        isActive = state.isActive,
        onClick = onClick,
    )
}

@Composable
fun Day(
    modifier: Modifier = Modifier,
    day: Int,
    isChosen: Boolean,
    isActive: Boolean,
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
            .size(48.dp)
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
    }
}

@ThemedPreview
@Composable
private fun DayPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            Box(modifier = Modifier.padding(16.dp)) {
                Day(
                    state = DayState(
                        day = LocalDate(2024, 10, 10),
                        isActive = true,
                        isChosen = false,
                    ),
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
                    onClick = {},
                )
            }
        }
    }
}
