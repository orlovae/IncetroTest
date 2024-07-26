package ru.alexandrorlov.incetrotest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import ru.alexandrorlov.incetrotest.App
import ru.alexandrorlov.incetrotest.common.di.Inject
import ru.alexandrorlov.incetrotest.common.di.daggerViewModel
import ru.alexandrorlov.incetrotest.common.model.ScreenState
import ru.alexandrorlov.incetrotest.detail.ui.DetailScreen
import ru.alexandrorlov.incetrotest.detail.ui.viewmodel.DetailViewModel
import ru.alexandrorlov.incetrotest.ui.theme.IncetroTestTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    init {
    Timber.tag("OAE").d("MainActivity start")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IncetroTestTheme {
                Timber.tag("OAE").d("MainActivity after IncetroTestTheme")
                Inject(
                    viewModelFactory = App.detailComponent.getViewModelFactory(),
                ) {
                    Timber.tag("OAE").d("MainActivity after Inject")
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
//                    val state = mainViewModel.state.collectAsState()
//                    val count = mainViewModel.countFavoriteIcon.collectAsState()

                        val detailViewModel: DetailViewModel = daggerViewModel()
                        val state = detailViewModel.state.collectAsState()
                        when (val data = state.value) {
                            ScreenState.Loading -> {
                            }

                            is ScreenState.Content -> {
                                DetailScreen(
                                    organization = data.content,
                                    onClick = {
                                        detailViewModel.onClickFavoriteIcon.tryEmit(it)
                                    },
                                )
//                            MainScreen(
//                                count = count.value,
//                                organizations = content.organizations,
//                                onClick = {
//                                    mainViewModel.onClickFavoriteIcon.tryEmit(it)
//                                }
//                            )
                            }

                            is ScreenState.Error -> {
                            }
                        }
                    }
                }
            }
        }
    }
}
