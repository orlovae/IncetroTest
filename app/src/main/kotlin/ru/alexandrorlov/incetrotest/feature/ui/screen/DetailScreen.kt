package ru.alexandrorlov.incetrotest.feature.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.common.di.daggerViewModel
import ru.alexandrorlov.incetrotest.common.model.ScreenState
import ru.alexandrorlov.incetrotest.common.model.SideEffect
import ru.alexandrorlov.incetrotest.feature.ui.component.common.SnackbarIT
import ru.alexandrorlov.incetrotest.feature.ui.component.detail.DescriptionCard
import ru.alexandrorlov.incetrotest.feature.ui.component.detail.HeaderCard
import ru.alexandrorlov.incetrotest.feature.ui.component.detail.PhotoItem
import ru.alexandrorlov.incetrotest.feature.ui.component.detail.TopBarDetail
import ru.alexandrorlov.incetrotest.feature.ui.models.OrganizationDetailUI
import ru.alexandrorlov.incetrotest.feature.ui.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    id: Long,
    navController: NavHostController,
    detailViewModel: DetailViewModel = daggerViewModel(),
){

    detailViewModel.idState.tryEmit(id)

    val state = detailViewModel.state.collectAsState()

    when (val data = state.value) {
        ScreenState.Loading -> {
        }

        is ScreenState.Content -> {
            val context: Context = LocalContext.current

            val snackbarHostState = remember { SnackbarHostState() }

            LaunchedEffect(key1 = Unit) {
                detailViewModel.sideEffect.collect { sideEffect ->
                    when (sideEffect) {

                        is SideEffect.SnackBar -> {
                            snackbarHostState.showSnackbar(
                                message = sideEffect.message,
                            )
                        }
                    }
                }
            }

            val organization: OrganizationDetailUI = data.content
            val onClickFavoriteIcon: MutableSharedFlow<Long> = detailViewModel.onClickFavoriteIcon

            Scaffold(
                topBar = {
                    TopBarDetail(
                        title = organization.categoryName.asString(context),
                        navController = navController,
                    )
                },
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState) { data ->
                        SnackbarIT(
                            snackBarText = data.visuals.message,
                        )
                    }
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
                        onClick = { onClickFavoriteIcon.tryEmit(it) },
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

        is ScreenState.Error -> {

        }
    }
}