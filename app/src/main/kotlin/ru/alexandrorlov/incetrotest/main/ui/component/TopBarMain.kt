package ru.alexandrorlov.incetrotest.main.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.ui.theme.Black
import ru.alexandrorlov.incetrotest.ui.theme.IconBackground
import ru.alexandrorlov.incetrotest.ui.theme.TypographyIncerto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    count: Int
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.restaurants),
                style = MaterialTheme.TypographyIncerto.titleTopBar,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = Black,
            ),
        actions = {
                  CountAllFavorite(count = count)
        },
    )
}

@Composable
private fun CountAllFavorite(
    count: Int,
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_favorite_24),
            contentDescription = "",
            tint = IconBackground,
        )
        Text(
            text = count.toString(),
            style = MaterialTheme.TypographyIncerto.countTopBar,
        )
    }
}