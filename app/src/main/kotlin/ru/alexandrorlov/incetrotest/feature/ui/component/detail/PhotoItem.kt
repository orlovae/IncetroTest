package ru.alexandrorlov.incetrotest.feature.ui.component.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.ui.theme.Black
import ru.alexandrorlov.incetrotest.ui.theme.White

@Composable
fun PhotoItem(
    urlPhoto: String,
){
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
            model = urlPhoto,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.height_photo_item)),
            error = painterResource(id = R.drawable.ic_launcher_error),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
    }
}