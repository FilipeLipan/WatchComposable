package com.github.filipelipan.composewatch

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class WatchMarkerStyle(
    open val size: Dp,
    open val color: androidx.compose.ui.graphics.Color,
    open val strokeWidth: Dp
) {
    class HourMarkerStyle(
        override val size: Dp = 25.dp,
        override val color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Black,
        override val strokeWidth: Dp = 3.dp
    ) : WatchMarkerStyle(
        size = size,
        color = color,
        strokeWidth = strokeWidth
    )

    class MinuteMarkerStyle(
        override val size: Dp = 12.dp,
        override val color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Black,
        override val strokeWidth: Dp = 2.dp
    ) : WatchMarkerStyle(
        size = size,
        color = color,
        strokeWidth = strokeWidth
    )
}
