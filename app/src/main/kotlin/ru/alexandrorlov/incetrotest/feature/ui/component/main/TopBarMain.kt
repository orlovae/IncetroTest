package ru.alexandrorlov.incetrotest.feature.ui.component.main

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.ui.theme.Black
import ru.alexandrorlov.incetrotest.ui.theme.IconBackground
import ru.alexandrorlov.incetrotest.ui.theme.TypographyIncerto
import ru.alexandrorlov.incetrotest.ui.theme.primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    count: Int,
    onClickAllFavorite: (Boolean) -> Unit,
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
                  CountAllFavorite(
                      count = count,
                      onClickAllFavorite = { onClickAllFavorite(it) },
                  )
        },
    )
}

@Composable
private fun CountAllFavorite(
    count: Int,
    onClickAllFavorite: (Boolean) -> Unit,
) {
    val selected: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .clickable {
                selected.value = selected.value.not()
                onClickAllFavorite(selected.value)
            },
        contentAlignment = Alignment.Center,
    ) {
        @DrawableRes
        val idIcon: Int = if (selected.value) {
            R.drawable.baseline_favorite_24
        } else {
            R.drawable.baseline_favorite_border_24
        }

        @ColorRes
        val textColorId: Color = if (selected.value) {
            primary
        } else {
            IconBackground
        }

        Icon(
            painter = painterResource(id = idIcon),
            contentDescription = "",
            tint = IconBackground,
        )
        Text(
            text = count.toString(),
            style = MaterialTheme.TypographyIncerto.countTopBar,
            color = textColorId,
        )
    }
}