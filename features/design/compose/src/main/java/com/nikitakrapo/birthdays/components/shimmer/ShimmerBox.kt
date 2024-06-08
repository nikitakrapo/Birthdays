package com.nikitakrapo.birthdays.components.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerBox(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .shimmer(),
    ) {
        Box(
            modifier = modifier,
        )
    }
}
