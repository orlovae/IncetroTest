package ru.alexandrorlov.incetrotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import ru.alexandrorlov.incetrotest.main.di.DaggerMainComponent
import ru.alexandrorlov.incetrotest.ui.component.main.CardItem
import ru.alexandrorlov.incetrotest.ui.component.main.TopBar
import ru.alexandrorlov.incetrotest.ui.models.MainState
import ru.alexandrorlov.incetrotest.ui.theme.IncetroTestTheme
import ru.alexandrorlov.incetrotest.ui.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = App.appComponent
        val mainComponent = DaggerMainComponent
            .factory()
            .create(appComponent)
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            IncetroTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state = viewModel.state.collectAsState()
                    val count = viewModel.countFavoriteIcon.collectAsState()
                    when (val content = state.value) {
                        MainState.Loading -> {
                        }
                        is MainState.Content -> {
                            Scaffold(
                                topBar = { TopBar(count = count.value) },
                            ) { innerPadding ->
                                LazyColumn(
                                    modifier = Modifier
                                        .padding(innerPadding),
                                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.extra_small_padding)),
                                    contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.extra_small_padding)),
                                ) {
                                    items(content.organizations) { organization ->
                                        CardItem(
                                            organization = organization,
                                            onClick = {
                                                viewModel.onClickFavoriteIcon.tryEmit(
                                                    organization.id
                                                )
                                            },
                                        )
                                    }
                                }
                            }
                        }
                        is MainState.Error -> {
                        }
                    }
                }
            }
        }
    }
}
