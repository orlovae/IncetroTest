package ru.alexandrorlov.incetrotest.feature.ui.component.detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.ui.theme.Black
import ru.alexandrorlov.incetrotest.ui.theme.TypographyIncerto
import ru.alexandrorlov.incetrotest.ui.theme.White

@Composable
fun DescriptionCard(
    description: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_radius))),
        colors = CardColors(
            containerColor = White,
            contentColor = Black,
            disabledContainerColor = White,
            disabledContentColor = Black,
        )
    ) {
        Text(
            text = stringResource(id = R.string.description),
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.medium_padding),
                    start = dimensionResource(id = R.dimen.medium_padding),
                    end = dimensionResource(id = R.dimen.medium_padding),
                ),
            style = MaterialTheme.TypographyIncerto.titleDescription,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )

        Spacer(modifier = Modifier
            .size(dimensionResource(id = R.dimen.extra_small_padding)),
        )

        Text(
            text = description,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.medium_padding),
                    end = dimensionResource(id = R.dimen.medium_padding),
                    bottom = dimensionResource(id = R.dimen.medium_padding),
                ),
            style = MaterialTheme.TypographyIncerto.description,
            textAlign = TextAlign.Left,
            overflow = TextOverflow.Visible,
            softWrap = false,
        )
    }
}