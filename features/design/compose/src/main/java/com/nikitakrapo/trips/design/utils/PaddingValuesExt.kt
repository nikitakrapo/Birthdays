package com.nikitakrapo.trips.design.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection

@Composable
operator fun PaddingValues.plus(other: PaddingValues) = PaddingValues(
    start = calculateStartPadding(LocalLayoutDirection.current)
            + other.calculateStartPadding(LocalLayoutDirection.current),
    top = calculateTopPadding()
            + other.calculateTopPadding(),
    end = calculateEndPadding(LocalLayoutDirection.current)
            + other.calculateEndPadding(LocalLayoutDirection.current),
    bottom = calculateBottomPadding()
            + other.calculateBottomPadding(),
)