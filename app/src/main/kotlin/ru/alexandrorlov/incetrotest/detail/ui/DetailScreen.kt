package ru.alexandrorlov.incetrotest.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.detail.ui.component.DescriptionCard
import ru.alexandrorlov.incetrotest.detail.ui.component.HeaderCard
import ru.alexandrorlov.incetrotest.detail.ui.component.PhotoItem
import ru.alexandrorlov.incetrotest.detail.ui.component.TopBarDetail
import ru.alexandrorlov.incetrotest.detail.ui.models.OrganizationDetailUI

@Composable
fun DetailScreen(
    organization: OrganizationDetailUI,
    onClick: (Long) -> Unit,
){
    val context = LocalContext.current
    
    Scaffold(
        topBar = {
            TopBarDetail(
                title = organization.categoryName.asString(context),
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.extra_small_padding)),
                contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.extra_small_padding)),
            ) {
                items(organization.photos) { urlPhoto ->
                    PhotoItem(
                        urlPhoto = urlPhoto,
                    )
                }
            }

            HeaderCard(
                organization = organization,
                onClick = onClick,
            )

            Spacer(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.extra_small_padding)),
            )

            DescriptionCard(
                description = organization.detailedInfo.asString(context)
            )
        }
    }
}