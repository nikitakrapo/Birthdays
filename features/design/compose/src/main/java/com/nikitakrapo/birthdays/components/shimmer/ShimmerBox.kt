package com.nikitakrapo.birthdays.components.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    shimmer: Shimmer? = null,
) {
    Box(
        modifier = Modifier
            .shimmer(shimmer),
    ) {
        Box(
            modifier = modifier
                .background(BirthdaysTheme.colorScheme.outline),
        )
    }
}
