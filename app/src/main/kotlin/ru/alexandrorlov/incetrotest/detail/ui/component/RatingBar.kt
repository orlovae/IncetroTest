package ru.alexandrorlov.incetrotest.detail.ui.component

import androidx.compose.foundation.layout.Box
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
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.ui.theme.IconBackground
import ru.alexandrorlov.incetrotest.ui.theme.TypographyIncerto
import ru.alexandrorlov.incetrotest.utils.Constant.STARS_RATING_COUNT

@Composable
fun RatingBar(
    modifier: Modifier,
    rate: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val rateInt: Int = rate.toInteger()
        val indexMax: Int = STARS_RATING_COUNT - 1

        for (i in 0..indexMax) {
            if (i < rateInt) {
                Star(type = TypeStar.STAR_FULL)
            } else {
                Star(type = TypeStar.STAR_EMPTY)
            }

            if (i < indexMax) {
                Spacer(modifier = Modifier
                    .size(dimensionResource(id = R.dimen.x_x_x_small_padding)),
                )
            }
        }

        Spacer(modifier = Modifier
            .size(dimensionResource(id = R.dimen.extra_small_padding)),
        )

        Text(
            text = rate,
            style = MaterialTheme.TypographyIncerto.ratingDetail,
            maxLines = 1,
        )
    }
}

@Composable
fun Star(type: TypeStar) {
    Box(
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.size_start))
    ) {
        when (type) {
            TypeStar.STAR_FULL -> Icon(
                painter = painterResource(id = R.drawable.baseline_star_full_16),
                tint = IconBackground,
                contentDescription = ""
            )
            TypeStar.STAR_EMPTY -> Icon(
                painter = painterResource(id = R.drawable.baseline_star_empty_16),
                tint = IconBackground,
                contentDescription = ""
            )
        }
    }
}

enum class TypeStar {
    STAR_FULL,
    STAR_EMPTY,
}

private fun String.toInteger(): Int =
    this.split(".").first().toInt()