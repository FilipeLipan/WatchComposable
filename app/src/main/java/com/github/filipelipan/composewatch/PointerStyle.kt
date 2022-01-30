package com.github.filipelipan.composewatch

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class PointerStyle(
    open val padding: Dp,
    open val color: androidx.compose.ui.graphics.Color,
    open val width: Dp,
) {
    class MinutePointerStyle(
        override val padding: Dp = 40.dp,
        override val color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Black,
        override val width: Dp = 2.dp
    ) : PointerStyle(
        padding = padding,
        color = color,
        width = width
    )

    class HourPointerStyle(
        override val padding: Dp = 60.dp,
        override val color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Black,
        override val width: Dp = 2.dp
    ) : PointerStyle(
        padding = padding,
        color = color,
        width = width
    )

    class SecondPointerStyle(
        override val padding: Dp = 30.dp,
        override val color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Red,
        override val width: Dp = 2.dp
    ) : PointerStyle(
        padding = padding,
        color = color,
        width = width
    )
}
