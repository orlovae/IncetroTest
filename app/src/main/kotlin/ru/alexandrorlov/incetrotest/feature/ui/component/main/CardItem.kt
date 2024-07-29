package ru.alexandrorlov.incetrotest.feature.ui.component.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.feature.ui.models.OrganizationUI
import ru.alexandrorlov.incetrotest.ui.theme.Black
import ru.alexandrorlov.incetrotest.ui.theme.IconBackground
import ru.alexandrorlov.incetrotest.ui.theme.TypographyIncerto
import ru.alexandrorlov.incetrotest.ui.theme.White

@Composable
fun CardItem(
    organization: OrganizationUI,
    navigateDetailScreen: (Long) -> Unit,
    onClickFavoriteIcon: (Long) -> Unit,
){
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_radius))),
        colors = CardColors(
            containerColor = White,
            contentColor = Black,
            disabledContainerColor = White,
            disabledContentColor = Black,
            )
    ) {
        AsyncImage(
            model = organization.photo,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.height_card_item))
                .clickable {
                    navigateDetailScreen(organization.id)
                },
            error = painterResource(id = R.drawable.ic_launcher_error),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )

        Spacer(modifier = Modifier
            .size(dimensionResource(id = R.dimen.small_padding)),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.medium_padding)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = organization.name.asString(context),
                modifier = Modifier
                    .weight(9f),
                style = MaterialTheme.TypographyIncerto.titleCard,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

            Icon(
                painter = painterResource(id = organization.idIcon),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .clickable { onClickFavoriteIcon(organization.id) },
                tint = IconBackground,
            )
        }

        Spacer(modifier = Modifier
            .size(dimensionResource(id = R.dimen.extra_small_padding)),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.medium_padding)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Rating(
                rate = organization.rate,
            )

            Spacer(modifier = Modifier
                .size(dimensionResource(id = R.dimen.extra_small_padding)),
            )

            Text(
                text = organization.averageCheck.asString(context),
                style = MaterialTheme.TypographyIncerto.descriptionCard,
            )

            Spacer(modifier = Modifier
                .size(dimensionResource(id = R.dimen.large_padding)),
            )

            Text(
                text = organization.cuisines.asString(context),
                style = MaterialTheme.TypographyIncerto.descriptionCard,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }

        Spacer(modifier = Modifier
            .size(dimensionResource(id = R.dimen.small_padding)))
    }
}