package ru.alexandrorlov.incetrotest.feature.ui.component.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.alexandrorlov.incetrotest.ui.theme.TypographyIncerto

@Composable
fun TextRating(
    rate: String,
) {
    Text(
        text = rate,
        style = MaterialTheme.TypographyIncerto.rating,
    )
}