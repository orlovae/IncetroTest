package ru.alexandrorlov.incetrotest.feature.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.common.di.daggerViewModel
import ru.alexandrorlov.incetrotest.common.model.ScreenState.Content
import ru.alexandrorlov.incetrotest.common.model.ScreenState.Error
import ru.alexandrorlov.incetrotest.common.model.ScreenState.Loading
import ru.alexandrorlov.incetrotest.common.model.SideEffect
import ru.alexandrorlov.incetrotest.feature.ui.component.common.SnackbarIT
import ru.alexandrorlov.incetrotest.feature.ui.component.main.CardItem
import ru.alexandrorlov.incetrotest.feature.ui.component.main.TopBar
import ru.alexandrorlov.incetrotest.feature.ui.models.OrganizationUI
import ru.alexandrorlov.incetrotest.feature.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(
    navigateDetailScreen: (Long) -> Unit,
    mainViewModel: MainViewModel = daggerViewModel(),
) {
    val state = mainViewModel.state.collectAsState()

    when (val data = state.value) {
        Loading -> {

        }

        is Content -> {
            val count = mainViewModel.countFavoriteIcon.collectAsState()
            val stateSideEffect = mainViewModel.sideEffect.collectAsState(initial = SideEffect.Init)

            val snackbarHostState = remember { SnackbarHostState() }

            when (val sideEffect = stateSideEffect.value) {
                SideEffect.Init -> Unit

                is SideEffect.SnackBar -> {
                    LaunchedEffect(key1 = Unit) {
                        snackbarHostState.showSnackbar(
                            message = sideEffect.message,
                        )
                    }
                }
            }

            Scaffold(
                topBar = {
                    TopBar(
                        count = count.value,
                        onClickAllFavorite = {
                            mainViewModel.onClickIconFavoriteTopbar.tryEmit(it)
                        },
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
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.extra_small_padding)),
                    contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.extra_small_padding)),
                ) {
                    val organizations: List<OrganizationUI> = data.content

                    items(organizations) { organization ->
                        CardItem(
                            organization = organization,
                            navigateDetailScreen = { navigateDetailScreen(it) },
                            onClickFavoriteIcon = { mainViewModel.onClickIconFavoriteItem.tryEmit(it) },
                        )
                    }
                }
            }
        }

        is Error -> {
        }
    }
}