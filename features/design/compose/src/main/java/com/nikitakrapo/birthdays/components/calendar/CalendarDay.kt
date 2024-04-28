package com.nikitakrapo.birthdays.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import com.nikitakrapo.trips.design.theme.TripsTheme

@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    day: Int,
    isChosen: Boolean = false,
    isActive: Boolean = true,
    onClick: () -> Unit,
) {
    val dayContentDescription = stringResource(strings.R.string.cd_day_of_month, day)
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(TripsTheme.shapes.small)
            .semantics { contentDescription = dayContentDescription }
            .clickable(onClick = onClick, enabled = isActive)
            .background(
                color = if (isChosen) {
                    BirthdaysTheme.colorScheme.surfaceContainer
                } else {
                    Color.Transparent
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = day.toString(),
            style = TripsTheme.typography.titleMedium,
            color = if (isActive) TripsTheme.colorScheme.onSurface else TripsTheme.colorScheme.outline
        )
    }
}

@Preview
@Composable
private fun CalendarDayPreview() {
    TripsTheme {
        Surface(color = TripsTheme.colorScheme.background) {
            CalendarDay(
                day = 23,
                onClick = {},
            )
        }
    }
}