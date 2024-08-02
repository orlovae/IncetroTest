package ru.alexandrorlov.incetrotest.feature.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.alexandrorlov.incetrotest.feature.di.bindmodule.MainBindModule
import ru.alexandrorlov.incetrotest.feature.domain.usecase.DetailUseCase
import ru.alexandrorlov.incetrotest.feature.domain.usecase.MainUseCase
import ru.alexandrorlov.incetrotest.feature.ui.viewmodel.DetailViewModel
import ru.alexandrorlov.incetrotest.feature.ui.viewmodel.MainViewModel

@Module(includes = [
    MainBindModule::class,
])
class FeatureModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Provides
    fun provideMainViewModel(mainUseCase: MainUseCase): ViewModel =
        MainViewModel(mainUseCase)

    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    @Provides
    fun provideManipulateViewModel(detailUseCase: DetailUseCase): ViewModel =
        DetailViewModel(detailUseCase)
}