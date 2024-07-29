package ru.alexandrorlov.incetrotest.feature.ui.component.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.ui.theme.Black
import ru.alexandrorlov.incetrotest.ui.theme.ShapesIncerto
import ru.alexandrorlov.incetrotest.ui.theme.TypographyIncerto
import ru.alexandrorlov.incetrotest.ui.theme.White

@Composable
fun SnackbarIT(
    snackBarText: String,
    bottomPadding: Int = R.dimen.small_snackbar_bottom_padding,
) {
    Snackbar(
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.medium_padding),
                end = dimensionResource(id = R.dimen.medium_padding),
                bottom = dimensionResource(bottomPadding),
            )
            .border(
                width = dimensionResource(id = R.dimen.border_thickness_snackbar),
                color = Black,
                shape = MaterialTheme.ShapesIncerto.shapeSnackbar,
            ),
        shape = MaterialTheme.ShapesIncerto.shapeSnackbar,
        containerColor = White,
        contentColor = Black,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = snackBarText,
            textAlign = TextAlign.Center,
            style = MaterialTheme.TypographyIncerto.textSnackbar,
        )
    }
}
