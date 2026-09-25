package com.example.ledproject

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun AppBackground() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(
            brush = Brush.verticalGradient(colors = listOf(Bg, BgLight, Bg))
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Copper.copy(alpha = 0.10f), Color.Transparent),
                center = Offset(size.width * 0.85f, size.height * 0.10f),
                radius = size.width * 0.9f
            ),
            radius = size.width * 0.9f,
            center = Offset(size.width * 0.85f, size.height * 0.10f)
        )
        val traceColor = Copper.copy(alpha = 0.06f)
        val step = size.height / 12
        for (i in 1..11) {
            val y = step * i
            drawLine(traceColor, Offset(0f, y), Offset(size.width * 0.3f, y), 1.5f)
            drawLine(traceColor, Offset(size.width * 0.3f, y), Offset(size.width * 0.3f, y - step * 0.4f), 1.5f)
            drawCircle(traceColor, radius = 4f, center = Offset(size.width * 0.3f, y - step * 0.4f))
        }
    }
}
