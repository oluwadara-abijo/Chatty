package com.fueled.chatty.core.ui.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class TicketShape(
    private val cornerRadius: Float,
    private val fractionPosition: Float,
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline = Outline.Generic(
        path = drawTicketPath(
            size = size,
            cornerRadius = cornerRadius,
            fractionPosition = fractionPosition,
        ),
    )

    private fun drawTicketPath(
        size: Size,
        cornerRadius: Float,
        fractionPosition: Float,
    ): Path =
        Path().apply {
            reset()
            lineTo(x = size.width, y = 0f)
            lineTo(x = size.width, y = (size.height * fractionPosition) - cornerRadius)
            arcTo(
                rect = Rect(
                    center = Offset(x = size.width, y = size.height * fractionPosition),
                    radius = cornerRadius,
                ),
                startAngleDegrees = TOP_ANGLE,
                sweepAngleDegrees = SWEEP_ANGLE,
                forceMoveTo = false,
            )
            lineTo(x = size.width, y = size.height)
            lineTo(x = 0f, y = size.height)
            lineTo(x = 0f, y = (size.height * fractionPosition) - cornerRadius)
            arcTo(
                rect = Rect(
                    center = Offset(x = 0f, y = size.height * fractionPosition),
                    radius = cornerRadius,
                ),
                startAngleDegrees = BOTTOM_ANGLE,
                sweepAngleDegrees = SWEEP_ANGLE,
                forceMoveTo = false,
            )
            lineTo(x = 0f, y = 0f)
            close()
        }
}

private const val TOP_ANGLE = 270f
private const val BOTTOM_ANGLE = 90f
private const val SWEEP_ANGLE = -180f
