package ru.alexandrorlov.incetrotest.feature.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.alexandrorlov.incetrotest.common.di.ViewModelAssistedFactory
import ru.alexandrorlov.incetrotest.common.di.ViewModelAssistedFactoryKey
import ru.alexandrorlov.incetrotest.common.di.ViewModelFactoryModule
import ru.alexandrorlov.incetrotest.feature.di.bindmodule.DetailBindModule
import ru.alexandrorlov.incetrotest.feature.di.bindmodule.MainBindModule
import ru.alexandrorlov.incetrotest.feature.ui.viewmodel.DetailViewModel
import ru.alexandrorlov.incetrotest.feature.ui.viewmodel.MainViewModel

@Module(includes = [
    MainBindModule::class,
    DetailBindModule::class,
    ViewModelFactoryModule::class,
])
interface FeatureModule {

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(MainViewModel::class)]
    fun bindsMainViewModelFactory(factory: MainViewModel.Factory) :
            ViewModelAssistedFactory<*>

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(DetailViewModel::class)]
    fun bindsDetailViewModelFactory(factory: DetailViewModel.Factory) :
            ViewModelAssistedFactory<*>
}