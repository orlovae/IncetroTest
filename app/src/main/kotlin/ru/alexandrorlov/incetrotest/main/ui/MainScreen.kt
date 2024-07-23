package ru.alexandrorlov.incetrotest.main.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.main.ui.component.CardItem
import ru.alexandrorlov.incetrotest.main.ui.component.TopBar
import ru.alexandrorlov.incetrotest.main.ui.models.OrganizationUi

@Composable
fun MainScreen(
    count: Int,
    organizations: List<OrganizationUi>,
    onClick: (Long) -> Unit,
) {
    Scaffold(
        topBar = { TopBar(count = count) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.extra_small_padding)),
            contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.extra_small_padding)),
        ) {
            items(organizations) { organization ->
                CardItem(
                    organization = organization,
                    onClick = { onClick(it) },
                )
            }
        }
    }
}