package com.github.filipelipan.composewatch

import androidx.compose.ui.geometry.Offset
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

object PolarCoordinateUtil {
    fun getOffsetByPolarCoordinate(radian: Float, radius: Float, circleCenter: Offset): Offset {
        val x = cos(radian) * radius + circleCenter.x
        val y = sin(radian) * radius + circleCenter.y
        return Offset(x, y)
    }

    fun degreeToRadian(degree: Int): Float {
        return (((degree) - 90) * ((PI / 180f).toFloat()))
    }
}