package com.nikitakrapo.birthdays.components.pager

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.theme.BirthdaysTheme

@Composable
fun HorizontalPagerIndicator(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentPage: Int,
    pageLimit: Int = Int.MAX_VALUE,
) {
    Row(
        modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { iteration ->
            val color = if (currentPage == iteration) {
                BirthdaysTheme.colorScheme.secondary
            } else if (iteration > pageLimit) {
                BirthdaysTheme.colorScheme.outline.copy(alpha = 0.5f)
            } else {
                BirthdaysTheme.colorScheme.outline
            }
            val animatedColor by animateColorAsState(targetValue = color)
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(animatedColor)
                    .size(6.dp)
            )
            if (iteration != pageCount - 1) {
                Spacer(modifier = Modifier.width(6.dp))
            }
        }
    }
}
