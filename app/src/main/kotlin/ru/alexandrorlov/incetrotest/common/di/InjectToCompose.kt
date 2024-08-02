package ru.alexandrorlov.incetrotest.common.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.alexandrorlov.incetrotest.feature.di.MultiViewModelFactory

@Composable
fun Inject(
    viewModelFactory: MultiViewModelFactory,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        value = LocalViewModelFactory provides viewModelFactory,
        content = content,
    )
}

@Composable
inline fun <reified VM : ViewModel> daggerViewModel(): VM {
    val factory = getViewModelFactory()
    return viewModel {
        factory.create(VM::class.java)
    }
}

@Composable
fun getViewModelFactory(): MultiViewModelFactory {
    return checkNotNull(LocalViewModelFactory.current) {
        "No ViewModelFactory was provided via LocalViewModelFactory"
    }
}

object LocalViewModelFactory {
    private val LocalViewModelFactory =
        compositionLocalOf<MultiViewModelFactory?> { null }

    val current: MultiViewModelFactory?
        @Composable
        get() = LocalViewModelFactory.current

    infix fun provides(viewModelFactory: MultiViewModelFactory):
            ProvidedValue<MultiViewModelFactory?> {
        return LocalViewModelFactory.provides(viewModelFactory)
    }
}