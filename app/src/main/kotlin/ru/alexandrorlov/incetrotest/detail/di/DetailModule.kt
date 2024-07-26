package ru.alexandrorlov.incetrotest.detail.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.alexandrorlov.incetrotest.common.di.ViewModelAssistedFactory
import ru.alexandrorlov.incetrotest.common.di.ViewModelAssistedFactoryKey
import ru.alexandrorlov.incetrotest.common.di.ViewModelFactoryModule
import ru.alexandrorlov.incetrotest.detail.ui.viewmodel.DetailViewModel

@Module(includes = [
    DetailBindModule::class,
    ViewModelFactoryModule::class,
])
interface DetailModule {

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(DetailViewModel::class)]
    fun bindsDetailViewModelFactory(factory: DetailViewModel.Factory):
            ViewModelAssistedFactory<*>
}