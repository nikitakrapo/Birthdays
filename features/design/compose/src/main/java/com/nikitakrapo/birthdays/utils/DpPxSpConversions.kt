package com.nikitakrapo.birthdays.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun TextUnit.toDpWithDensity(): Dp {
    val density = LocalDensity.current
    return with(density) { toDp() }
}