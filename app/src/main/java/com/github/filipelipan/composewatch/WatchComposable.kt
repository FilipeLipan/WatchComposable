package com.github.filipelipan.composewatch

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.filipelipan.composewatch.ui.theme.ComposeWatchTheme
import kotlinx.coroutines.delay

@Composable
fun WatchComposable(
    modifier: Modifier = Modifier,
    padding: Dp = 10.dp,
    hourMarkerStyle: WatchMarkerStyle.HourMarkerStyle = WatchMarkerStyle.HourMarkerStyle(),
    minuteMarkerStyle: WatchMarkerStyle.MinuteMarkerStyle = WatchMarkerStyle.MinuteMarkerStyle(),
    minutePointerStyle: PointerStyle = PointerStyle.MinutePointerStyle(),
    secondPointerStyle: PointerStyle = PointerStyle.SecondPointerStyle(),
    hourPointerStyle: PointerStyle = PointerStyle.HourPointerStyle(),
) {
    var hours by remember { mutableStateOf(0) }
    var minutes by remember { mutableStateOf(59) }
    var seconds by remember { mutableStateOf(0) }

    LaunchedEffect(hours, minutes, seconds) {
        delay(1000L)
        seconds++
        if (seconds % 60 == 0) {
            minutes++
            if (minutes % 60 == 0) {
                hours++
            }
        }
    }

    Canvas(
        modifier = modifier
    ) {
        val circleCenter = this.center
        val radius = (this.size.height / 2) - (padding.toPx() * 2)

        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                center.x,
                center.y,
                radius,
                Paint().apply {
                    color = Color.WHITE
                    setShadowLayer(
                        50f,
                        0f,
                        0f,
                        Color.argb(50, 0, 0, 0)
                    )
                }
            )
        }

        for (i in 1..60) {
            val radian = PolarCoordinateUtil.degreeToRadian((360 / 60) * i)
            val watchMarkerStyle = if ((i % 5) == 0) {
                hourMarkerStyle
            } else {
                minuteMarkerStyle
            }

            val outOffset = PolarCoordinateUtil.getOffsetByPolarCoordinate(
                radian = radian,
                radius = radius,
                circleCenter = circleCenter
            )
            val inOffset = PolarCoordinateUtil.getOffsetByPolarCoordinate(
                radian = radian,
                radius = radius - watchMarkerStyle.size.toPx(),
                circleCenter = circleCenter
            )

            drawLine(
                color = watchMarkerStyle.color,
                strokeWidth = watchMarkerStyle.strokeWidth.toPx(),
                start = outOffset,
                end = inOffset
            )
        }

        drawPointer(
            pointerStyle = secondPointerStyle,
            radianAngle = PolarCoordinateUtil.degreeToRadian((360 / 60) * seconds),
            drawScope = this,
            circleCenter = circleCenter,
            radius = radius,
            toPx = { dp -> dp.toPx() }
        )

        drawPointer(
            pointerStyle = minutePointerStyle,
            radianAngle = PolarCoordinateUtil.degreeToRadian((360 / 60) * minutes),
            drawScope = this,
            circleCenter = circleCenter,
            radius = radius,
            toPx = { dp -> dp.toPx() }
        )

        drawPointer(
            pointerStyle = hourPointerStyle,
            radianAngle = PolarCoordinateUtil.degreeToRadian((360 / 12) * hours),
            drawScope = this,
            circleCenter = circleCenter,
            radius = radius,
            toPx = { dp -> dp.toPx() }
        )
    }
}

private fun drawPointer(
    pointerStyle: PointerStyle,
    radianAngle: Float,
    radius: Float,
    drawScope: DrawScope,
    circleCenter: Offset,
    toPx: (dp: Dp) -> Float
) {
    val outOffset = PolarCoordinateUtil.getOffsetByPolarCoordinate(
        radian = radianAngle,
        radius = radius - toPx(pointerStyle.padding),
        circleCenter = circleCenter
    )

    drawScope.drawLine(
        color = pointerStyle.color,
        strokeWidth = toPx(pointerStyle.width),
        start = circleCenter,
        end = outOffset
    )
}

@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
fun DefaultPreview() {
    ComposeWatchTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            WatchComposable(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}