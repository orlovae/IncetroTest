package ru.alexandrorlov.incetrotest.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class ShapesIncerto(
    val shapeSnackbar: Shape = RoundedCornerShape(
        size = 30.dp,
    ),
)

val LocalShapes = staticCompositionLocalOf { ShapesIncerto() }

val MaterialTheme.ShapesIncerto
    @Composable
    @ReadOnlyComposable
    get() = LocalShapes.current
