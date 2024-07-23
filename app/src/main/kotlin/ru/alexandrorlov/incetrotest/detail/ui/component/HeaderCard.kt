package ru.alexandrorlov.incetrotest.detail.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.detail.ui.models.OrganizationDetailUI
import ru.alexandrorlov.incetrotest.ui.theme.Black
import ru.alexandrorlov.incetrotest.ui.theme.IconBackground
import ru.alexandrorlov.incetrotest.ui.theme.TypographyIncerto
import ru.alexandrorlov.incetrotest.ui.theme.White

@Composable
fun HeaderCard(
    organization: OrganizationDetailUI,
    onClick: (Long) -> Unit,
) {
    val context = LocalContext.current

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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.medium_padding),
                    start = dimensionResource(id = R.dimen.medium_padding),
                    end = dimensionResource(id = R.dimen.medium_padding),
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = organization.name.asString(context),
                modifier = Modifier
                    .weight(9f),
                style = MaterialTheme.TypographyIncerto.titleHeader,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

            Icon(
                painter = painterResource(id = organization.idIcon),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .clickable { onClick(organization.id) },
                tint = IconBackground,
            )
        }

        Spacer(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.extra_small_padding)),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.medium_padding),
                    end = dimensionResource(id = R.dimen.medium_padding),
                    bottom = dimensionResource(id = R.dimen.medium_padding),
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RatingBar(
                modifier = Modifier
                    .weight(9f),
                rate = organization.rate,
            )

            Text(
                text = organization.averageCheck.asString(context),
                modifier = Modifier
                    .weight(1f),
                style = MaterialTheme.TypographyIncerto.descriptionCard,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }
}