package ru.alexandrorlov.incetrotest.feature.ui.component.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.ui.theme.IconBackground
import ru.alexandrorlov.incetrotest.ui.theme.TypographyIncerto

@Composable
fun Rating(
    rate: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (rate.isNotBlank()) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_rate_24),
                contentDescription = "",
                tint = IconBackground,
            )

            Spacer(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.x_x_small_padding)),
            )

            TextRating(
                rate = rate,
            )
        } else {
            TextRating(
                rate = stringResource(id = R.string.no_rate),
            )
        }
    }
}

@Composable
private fun TextRating(
    rate: String,
) {
    Text(
        text = rate,
        style = MaterialTheme.TypographyIncerto.ratingMain,
    )
}