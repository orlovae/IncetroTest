package ru.alexandrorlov.incetrotest.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import ru.alexandrorlov.incetrotest.App
import ru.alexandrorlov.incetrotest.common.model.ScreenState
import ru.alexandrorlov.incetrotest.detail.di.DaggerDetailComponent
import ru.alexandrorlov.incetrotest.detail.ui.DetailScreen
import ru.alexandrorlov.incetrotest.detail.ui.viewmodel.DetailViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
//    @Inject
//    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = App.appComponent
//        val mainComponent = DaggerMainComponent
//            .factory()
//            .create(appComponent)
//        mainComponent.inject(this)

        val detailComponent = DaggerDetailComponent
            .factory()
            .create(appComponent)

        detailComponent.inject(this)

        super.onCreate(savedInstanceState)

        setContent {
            IncetroTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val state = mainViewModel.state.collectAsState()
//                    val count = mainViewModel.countFavoriteIcon.collectAsState()

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
