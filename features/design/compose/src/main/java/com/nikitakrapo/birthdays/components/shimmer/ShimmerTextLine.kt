package com.nikitakrapo.birthdays.components.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import com.nikitakrapo.birthdays.utils.toDpWithDensity
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerTextLine(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    isAllCaps: Boolean = false,
    shimmer: Shimmer? = null,
) {
    val shimmerHeight = remember(isAllCaps, textStyle) {
        if (isAllCaps) textStyle.lineHeight else textStyle.fontSize
    }
    Box(
        modifier = Modifier
            .height(textStyle.lineHeight.toDpWithDensity())
            .shimmer(shimmer),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = modifier
                .height(shimmerHeight.toDpWithDensity())
                .clip(RoundedCornerShape(8.dp))
                .background(BirthdaysTheme.colorScheme.outline),
        )
    }
}