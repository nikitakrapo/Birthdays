package com.nikitakrapo.birthdays.components.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import com.nikitakrapo.birthdays.utils.toDpWithDensity
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerTextLine(
    modifier: Modifier = Modifier,
    textStyle: TextStyle,
    width: Dp = 200.dp,
) {
    Box(modifier = modifier.shimmer()) {
        Box(
            modifier = Modifier
                .height(textStyle.lineHeight.toDpWithDensity())
                .width(width)
                .clip(RoundedCornerShape(8.dp))
                .background(BirthdaysTheme.colorScheme.outline),
        )
    }
}