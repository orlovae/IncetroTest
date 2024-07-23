package ru.alexandrorlov.incetrotest.main.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.alexandrorlov.incetrotest.di.ViewModelKey
import ru.alexandrorlov.incetrotest.main.domain.usecase.MainUseCase
import ru.alexandrorlov.incetrotest.main.ui.viewmodel.MainViewModel

@Module(includes = [MainBindModule::class])
class MainModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Provides
    fun provideMainViewModel(mainUseCase: MainUseCase): ViewModel =
        MainViewModel(mainUseCase)
}