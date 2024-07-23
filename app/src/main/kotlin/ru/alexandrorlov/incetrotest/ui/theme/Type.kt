package ru.alexandrorlov.incetrotest.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography()

data class TypographyIncerto(
    val titleTopBar: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        color = Black,
    ),
    val countTopBar: TextStyle = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 10.sp,
        color = White,
    ),
    val titleCard: TextStyle = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 17.sp,
    ),
    val descriptionCard: TextStyle = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
        color = GrayText,
    ),
    val ratingMain: TextStyle = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 15.sp,
        color = Black,
    ),
    val titleHeader: TextStyle = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
        color = Black,
    ),
    val ratingDetail: TextStyle = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 15.sp,
        color = IconBackground,
    ),
    val titleDescription: TextStyle = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 17.sp,
        color = Black,
    ),
    val description: TextStyle = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
        color = GrayText,
    ),
)

val LocalTextStyle = staticCompositionLocalOf { TypographyIncerto() }

val MaterialTheme.TypographyIncerto
    @Composable
    @ReadOnlyComposable
    get() = LocalTextStyle.current